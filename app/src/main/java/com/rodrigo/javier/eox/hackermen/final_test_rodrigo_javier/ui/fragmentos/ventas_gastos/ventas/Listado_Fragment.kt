package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ventas_gastos.ventas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.data_view.Productos_DataView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.DataEjemplo.*
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters.Ventas_Invent_Adapter

class Listado_Fragment : Fragment() {

    private lateinit var recycler_inventario: RecyclerView
    private lateinit var adapter: Ventas_Invent_Adapter
    private lateinit var listado_productos: ArrayList<Productos_DataView>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.listado_inventario_fragment, container, false)
        super.onCreateView(inflater, container, savedInstanceState)

        recycler_inventario = view.findViewById(R.id.recycler_listado_inventario)
        recycler_inventario.hasFixedSize()
        var divider =
            DividerItemDecoration(recycler_inventario.context, 1)
        recycler_inventario.addItemDecoration(divider)
        recycler_inventario.layoutManager = LinearLayoutManager(requireContext())
        recycler_inventario.adapter = Ventas_Invent_Adapter(llenadoListaProductos())


        return view
    }

    private fun llenadoListaProductos(): ArrayList<Productos_DataView> {
        return arrayListOf(
            producto_1,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_2,
            producto_3,
            producto_4,
            producto_5
        )
    }

    companion object {
        fun newInstance(): Listado_Fragment {
            var newListado_Fragment = Listado_Fragment()

            return newListado_Fragment
        }
    }
}