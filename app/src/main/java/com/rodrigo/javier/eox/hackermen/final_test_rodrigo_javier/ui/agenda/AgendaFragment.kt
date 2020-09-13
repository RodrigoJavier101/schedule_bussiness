package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.agenda

import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.EJEMPLO_LIVE_CYCLE.EventLogger
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.EJEMPLO_LIVE_CYCLE.LifeCycleCustomObservation
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.gestion_data_view.Agenda_DataView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.adapters.AgendaRecyclerAdapter
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionDao
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionDatabase
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.RoomApplication
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.entities.Agenda_Entity
import kotlinx.android.synthetic.main.add_asundo_agenda_layout_fake.view.*


class AgendaFragment : Fragment() {

    private lateinit var agendaViewModel: AgendaViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: AgendaRecyclerAdapter
    private lateinit var dataBase: GestionDatabase
    private lateinit var dao: GestionDao
    private lateinit var floatingActionButton: FloatingActionButton

    /*+++++++++++++++++++++++++++++++++++++++++++++++++*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        agendaViewModel =
            ViewModelProviders.of(this).get(AgendaViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_agenda_fake, container, false)
//        val textView: TextView = view.findViewById(R.id.)
        agendaViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it

        })

        val observer = LifeCycleCustomObservation(lifecycle, EventLogger())
        lifecycle.addObserver(observer)

        dataBase = RoomApplication.gestionDatabase!!
        dao = dataBase.getGestionDao()
        setUpViews(view)
        return view
    }

    private fun setUpViews(view: View) {
        recycler = view.findViewById(R.id.rv_asunto_agenda_list)
        recycler.layoutManager = LinearLayoutManager(context)
        floatingActionButton = view.findViewById(R.id.fab)
        setUpAddButton()
        adapter = AgendaRecyclerAdapter(mutableListOf(), requireContext())
        recycler.adapter = adapter
    }

    private fun setUpAddButton() {

        floatingActionButton.setOnClickListener {
            val dialogView = layoutInflater
                .inflate(R.layout.add_asundo_agenda_layout_fake, null)
            val agendaAsuntoInput = dialogView.agenda_asunto_input
            val dialogBuilder = AlertDialog
                .Builder(requireContext())
                .setTitle("Agrega una Asunto a la Agenda")
                .setView(dialogView)
                .setNegativeButton("Cerrar") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
                .setPositiveButton("Agregar") { dialog: DialogInterface, _: Int ->

                    if (agendaAsuntoInput.text?.isNotEmpty()!!) {
                        AsyncTask.execute {
                            val newItems = createEntityListFromDatabase(dao.getAllFromAgendaTable())
//                            var recomm =
//                                view?.findViewById<TextInputEditText>(R.id.agendamiento_box)

                            if (agendaAsuntoInput != null) {
                                dao.insertAgenda(createEntity(agendaAsuntoInput.text.toString()))
                            }

                            var thread = Thread() {
                                fun run() {
                                    try {
                                        synchronized(this) {
                                            Thread.sleep(500)
                                            runOnUiThread {
                                                adapter.updateData(newItems)
                                                dialog.dismiss()
                                            }
                                        }
                                    } catch (e: InterruptedException) {

                                    }
                                }
                            }
                            thread.start()
                        }
                    }

                }
            dialogBuilder.create().show()
        }

    }

    private fun createEntity(a_a: String): Agenda_Entity {
        return Agenda_Entity(asunto_agenda = a_a)
    }

    private fun createEntityListFromDatabase(registros_db: List<Agenda_Entity>):
            MutableList<Agenda_DataView> {
        val dataList = mutableListOf<Agenda_DataView>()
        if (registros_db.isNotEmpty()) {
            for (entity in registros_db) {
                val dataView = Agenda_DataView(
                    entity.id_agenda,
                    entity.fecha_programada.toString(),
                    entity.asunto_agenda
                )
                dataList.add(dataView)
            }
        }
        return dataList
    }


    companion object {
        fun newInstance(): AgendaFragment {
            var newAgendaFragment = AgendaFragment()
            return newAgendaFragment
        }
    }
}