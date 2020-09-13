package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ingresos.fragments_interiores

import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.adapters.AgendaRecyclerAdapter
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionDao
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionDatabase
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.RoomApplication


class AgregarAVentas_Fragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: AgendaRecyclerAdapter
    private lateinit var dataBase: GestionDatabase
    private lateinit var dao: GestionDao
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = inflater.inflate(R.layout.agregar_a_ventas_fragment, container, false)


        dataBase = RoomApplication.gestionDatabase!!
        dao = dataBase.getGestionDao()
//        setUpViews(view)
        return view
    }

    companion object {
        fun newInstance(): AgregarAVentas_Fragment {
            var newAgregarAVentas_Fragment = AgregarAVentas_Fragment()

            return newAgregarAVentas_Fragment
        }
    }


//    private fun setUpAddButton() {
//        floatingActionButton.setOnClickListener {
//            val dialogView = layoutInflater
//                .inflate(R.layout.add_asundo_agenda_layout_fake, null)
//            val agendaAsuntoInput = dialogView.agenda_asunto_input
//            val dialogBuilder = AlertDialog
//                .Builder(requireContext())
//                .setTitle("Agrega una Asunto a la Agenda")
//                .setView(dialogView)
//                .setNegativeButton("Cerrar") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
//                .setPositiveButton("Agregar") { dialog: DialogInterface, _: Int ->
//
//                    if (agendaAsuntoInput.text?.isNotEmpty()!!) {
//                        AsyncTask.execute {
//                            val newItems = createEntityListFromDatabase(dao.getAllFromAgendaTable())
//                            //                            var recomm =
//                            //                                view?.findViewById<TextInputEditText>(R.id.agendamiento_box)
//
//                            if (agendaAsuntoInput != null) {
//                                dao.insertAgenda(createEntity(agendaAsuntoInput.text.toString()))
//                            }
//
//                            var thread = Thread() {
//                                fun run() {
//                                    try {
//                                        synchronized(this) {
//                                            Thread.sleep(500)
//                                            UiThreadStatement.runOnUiThread {
//                                                adapter.updateData(newItems)
//                                                dialog.dismiss()
//                                            }
//                                        }
//                                    } catch (e: InterruptedException) {
//
//                                    }
//                                }
//                            }
//                            thread.start()
//                        }
//                    }
//
//                }
//            dialogBuilder.create().show()
//        }
//
//    }

//    private fun createEntity(a_a: String): Agenda_Entity {
//        return Agenda_Entity(asunto_agenda = a_a)
//    }

//    private fun createEntityListFromDatabase(registros_db: List<Agenda_Entity>):
//            MutableList<Agenda_DataView> {
//        val dataList = mutableListOf<Agenda_DataView>()
//        if (registros_db.isNotEmpty()) {
//            for (entity in registros_db) {
//                val dataView = Agenda_DataView(
//                    entity.id_agenda,
//                    entity.fecha_programada.toString(),
//                    entity.asunto_agenda
//                )
//                dataList.add(dataView)
//            }
//        }
//        return dataList
//}


//    private fun createEntity(a_a: String): Agenda_Entity {
//        return Agenda_Entity(asunto_agenda = a_a)
//    }

//    private fun createEntityListFromDatabase(registros_db: List<Agenda_Entity>):
//            MutableList<Agenda_DataView> {
//        val dataList = mutableListOf<Agenda_DataView>()
//        if (registros_db.isNotEmpty()) {
//            for (entity in registros_db) {
//                val dataView = Agenda_DataView(
//                    entity.id_agenda,
//                    entity.fecha_programada.toString(),
//                    entity.asunto_agenda
//                )
//                dataList.add(dataView)
//            }
//        }
//        return dataList
//    }
}
