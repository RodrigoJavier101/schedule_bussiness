package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ventas_gastos.ventas.fragment_interiores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.interfaces.CardViewListenerLongClick
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.interfaces.CardViewListenerShortClick
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionDao
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Productos_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.RoomApplication
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters.Lista_Inventario_Adapter
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.view_models.ListaViewModel
import kotlinx.android.synthetic.main.item_listado_inventario.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Listado_Fragment : Fragment(), CardViewListenerShortClick, CardViewListenerLongClick {

    private lateinit var adapter: Lista_Inventario_Adapter

    //    private var listado_productos: MutableList<Productos_DataView> = mutableListOf()
    private val dao: GestionDao = RoomApplication.gestionDatabase.getGestionDao()
    private lateinit var recycler_inventario: RecyclerView
    private lateinit var model: ListaViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.listado_inventario_fragment, container, false)
        super.onCreateView(inflater, container, savedInstanceState)
        model =
            ViewModelProvider(requireActivity()).get(ListaViewModel::class.java)
        recycler_inventario = view.findViewById(R.id.recycler_listado_inventario)
        recycler_inventario.hasFixedSize()
        val divider =
            DividerItemDecoration(recycler_inventario.context, 1)
        recycler_inventario.addItemDecoration(divider)
        recycler_inventario.layoutManager = LinearLayoutManager(requireContext())

        CoroutineScope(Dispatchers.IO).launch {
            val productos_ddbb = createProductListFromDatabase()
            adapter = Lista_Inventario_Adapter(
                productos_ddbb,
                this@Listado_Fragment,
                this@Listado_Fragment
            )
            recycler_inventario.adapter = adapter
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

    override fun cardViewClickedShort(producto: Productos_Entity, view: View, position: Int) {
        model.setSelected(producto)
    }

    override fun cardViewClickedLong(producto: Productos_Entity) {
        deleteItem(producto)
        Toast.makeText(context, "Eliminado ${producto.nombre_producto}", Toast.LENGTH_SHORT).show()
    }

    fun deleteItem(producto: Productos_Entity) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteProducto(producto)
        }
    }

}