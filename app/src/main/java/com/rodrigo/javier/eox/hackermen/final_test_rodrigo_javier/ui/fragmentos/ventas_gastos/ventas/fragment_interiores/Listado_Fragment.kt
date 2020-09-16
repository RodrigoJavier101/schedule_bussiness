package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ventas_gastos.ventas.fragment_interiores

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Productos_DataView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.DataEjemplo.*
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionDao
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Productos_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.RoomApplication
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters.Ventas_Invent_Adapter

class Listado_Fragment : Fragment() {


    private lateinit var recycler_inventario: RecyclerView
    private lateinit var adapter: Ventas_Invent_Adapter
    private lateinit var listado_productos: ArrayList<Productos_DataView>
    private lateinit var model: Adapter_Listado_View_Model
    private val dao: GestionDao = RoomApplication.gestionDatabase.getGestionDao()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.listado_inventario_fragment, container, false)
        super.onCreateView(inflater, container, savedInstanceState)
        model = ViewModelProviders.of(this).get(Adapter_Listado_View_Model::class.java)

        recycler_inventario = view.findViewById(R.id.recycler_listado_inventario)
        recycler_inventario.hasFixedSize()
        var divider =
            DividerItemDecoration(recycler_inventario.context, 1)
        recycler_inventario.addItemDecoration(divider)
        recycler_inventario.layoutManager = LinearLayoutManager(requireContext())
        recycler_inventario.adapter = Ventas_Invent_Adapter(createProductListFromDatabase())

        return view
    }

    companion object {
        fun newInstance(): Listado_Fragment {
            var newListado_Fragment = Listado_Fragment()

            return newListado_Fragment
        }
    }

    private fun createProductListFromDatabase():
            List<Productos_Entity> {
        var registros_db = dao.getAllFromProductosTable()
        var dataList: List<Productos_Entity> = emptyList()

        registros_db.observe(viewLifecycleOwner, Observer {
            dataList = it
        })
        Log.d("-----------DATA LIST------------->", dataList.toString())
        dataList = listOf(producto_1)
        Log.d("-----------DATA LIST------------->", dataList.toString())
        return dataList
    }
}