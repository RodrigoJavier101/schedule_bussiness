package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ventas_gastos.ventas.fragment_interiores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.*
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters.FromListaToAgregados_Adapter
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.view_models.ListaViewModel


class AgregarAVentas_Fragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: FromListaToAgregados_Adapter
    private lateinit var dataBase: GestionDatabase
    private lateinit var dao: GestionDao
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var lista_productos: ArrayList<Productos_Entity>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.agregar_a_ventas_fragment, container, false)

        dataBase = RoomApplication.gestionDatabase!!
        dao = dataBase.getGestionDao()


        recycler = view.findViewById(R.id.recyclerview_agregados_a_ventas)
        recycler.hasFixedSize()
        val divider = DividerItemDecoration(recycler.context, 1)
        recycler.addItemDecoration(divider)
        recycler.layoutManager = LinearLayoutManager(requireContext())
//        recycler.layoutManager =
//            GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        lista_productos = arrayListOf()

        return view
    }

    companion object {
        fun newInstance(): AgregarAVentas_Fragment {
            var newAgregarAVentas_Fragment = AgregarAVentas_Fragment()

            return newAgregarAVentas_Fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: ListaViewModel =
            ViewModelProvider(requireActivity()).get(ListaViewModel::class.java)
        viewModel.getSelected()?.observe(viewLifecycleOwner) { item ->
            Toast.makeText(context, item.nombre_producto, Toast.LENGTH_SHORT).show()
            lista_productos.add(item)
            adapter = FromListaToAgregados_Adapter(lista_productos)
            recycler.adapter = adapter
        }

    }


//    private fun setUpAddButton() {
//        floatingActionButton.setOnClickListener {
//            val dialogView = layoutInflater
//                .inflate(R.layout.add_producto_dialog, null)
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
}
