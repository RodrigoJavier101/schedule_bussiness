package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ventas_gastos.ventas.fragment_interiores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.*
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters.Lista_Inventario_Adapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Listado_Fragment : Fragment() {

    private val adapter = Lista_Inventario_Adapter(mutableListOf())
    private var listado_productos: MutableList<Productos_DataView> = mutableListOf()
    private val dao: GestionDao = RoomApplication.gestionDatabase.getGestionDao()
    private lateinit var recycler_inventario: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.listado_inventario_fragment, container, false)
        super.onCreateView(inflater, container, savedInstanceState)

        recycler_inventario = view.findViewById(R.id.recycler_listado_inventario)
        recycler_inventario.adapter = adapter
        recycler_inventario.hasFixedSize()
        var divider =
            DividerItemDecoration(recycler_inventario.context, 1)
        recycler_inventario.addItemDecoration(divider)
        recycler_inventario.layoutManager = LinearLayoutManager(requireContext())

        CoroutineScope(Dispatchers.IO).launch {
            val productos_ddbb = createProductListFromDatabase()

            recycler_inventario.adapter =
                Lista_Inventario_Adapter(productos_ddbb)

        }
        return view
    }


    companion object {
        fun newInstance(): Listado_Fragment {
            var newListado_Fragment = Listado_Fragment()
            return newListado_Fragment
        }
    }

    suspend fun createProductListFromDatabase():
            List<Productos_Entity> {

        val registros_db = dao.getAllFromProductosTable()

        return registros_db
    }
}