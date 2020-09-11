package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.agenda

import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.adapters.AgendaRecyclerAdapter
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionDao
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionDatabase
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.RoomApplication
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.entities.Agenda
import kotlinx.android.synthetic.main.alertdialog_agenda.view.*
import kotlinx.android.synthetic.main.item_dialog_agenda.*

class AgendaFragment : Fragment() {

    private lateinit var agendaViewModel: AgendaViewModel
    private lateinit var list_recycler_view: RecyclerView
    private lateinit var adapter_agenda: AgendaRecyclerAdapter
    private lateinit var dataBase_agenda: GestionDatabase
    private lateinit var dao: GestionDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        agendaViewModel =
            ViewModelProviders.of(this).get(AgendaViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_agenda, container, false)
//        val textView: TextView = view.findViewById(R.id.text_agenda)
        agendaViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })

        list_recycler_view.layoutManager = LinearLayoutManager(context)
        adapter_agenda = AgendaRecyclerAdapter(mutableListOf(), requireContext())
        list_recycler_view.adapter = adapter_agenda

        agenda_Box.setOnClickListener {
            val dialogView = layoutInflater
                .inflate(R.layout.item_dialog_agenda, null)
            val recommendationText = dialogView.agenda_input
            val dialogBuilder = AlertDialog
                .Builder(requireContext())
                .setTitle("Agrega una recomendación")
                .setView(dialogView)
                .setNegativeButton("Cerrar") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
                .setPositiveButton("Agregar") { dialog: DialogInterface, _: Int ->
                    if (recommendationText.text?.isNotEmpty()!!) {
                        AsyncTask.execute {
                            dao.insertAgenda(createEntity("","","")
                            val newItems = createEntityListFromDatabase(dao.getAllFromAgendaTable())
                            runOnUiThread {
                                adapter_agenda.upDateData(newItems)
                                dialog.dismiss()
                            }
                        }
                    }
                }
            dialogBuilder.create().show()
        }

        dataBase_agenda = RoomApplication.gestionDatabase!!
        dao = dataBase_agenda.getGestionDao()

        return view
    }


    private fun createEntity(
        id_agenda_: String,
        fecha_programada_: String,
        asunto_agenda_: String
    ): Agenda {
        return Agenda(
            id_agenda = id_agenda_.toInt(), fecha_programada = fecha_programada_,
            asunto_agenda = asunto_agenda_
        )
    }

    private fun createEntityListFromDatabase(entities: List<Agenda>):
            MutableList<Agenda> {
        val dataList = mutableListOf<Agenda>()
        if (entities.isNotEmpty()) {
            for (entity in entities) {
                val dataView = Agenda(
                    entity.id_agenda,
                    entity.fecha_programada,
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