package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ventas_gastos.ventas.fragment_interiores

import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.GestionDao
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.GestionDatabase
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Productos_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.interfaces.CardViewListenerLongClick
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.interfaces.CardViewListenerShortClick
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ventas_gastos.adapters.Lista_Inventario_Adapter
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.view_models.ListaViewModel
import kotlinx.android.synthetic.main.add_producto_dialog.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ListadoFragment : Fragment(), CardViewListenerShortClick, CardViewListenerLongClick {

    private lateinit var adapter: Lista_Inventario_Adapter
    private lateinit var dao: GestionDao
    private lateinit var recycler_inventario: RecyclerView
    private lateinit var model: ListaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.listado_inventario_fragment, container, false)
        super.onCreateView(inflater, container, savedInstanceState)
        dao = GestionDatabase.getInstance(requireContext())!!.setDao()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewAttached(view)
    }

    private fun onViewAttached(view: View) {
        model =
            ViewModelProvider(requireActivity()).get(ListaViewModel::class.java)
        recycler_inventario = view.findViewById(R.id.recycler_listado_inventario)
        recycler_inventario.hasFixedSize()
//        val divider =
//            DividerItemDecoration(recycler_inventario.context, 1)
//        recycler_inventario.addItemDecoration(divider)
        recycler_inventario.layoutManager = LinearLayoutManager(requireContext())

        CoroutineScope(Dispatchers.IO).launch {
            val productos_ddbb = createProductListFromDatabase()
            adapter = Lista_Inventario_Adapter(
                productos_ddbb,
                this@ListadoFragment,
                this@ListadoFragment
            )
            recycler_inventario.adapter = adapter
        }

        setUpAddButton(view)
    }

    companion object {
        fun newInstance(): ListadoFragment {
            var newListado_Fragment = ListadoFragment()
            return newListado_Fragment
        }
    }

    fun createProductListFromDatabase():
            List<Productos_Entity> {

        val registros_db = dao.getAllProductos_2()

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

    private fun setUpAddButton(view: View) {
        var btn_agregar = view.findViewById<Button>(R.id.btn_agregar_producto)
        btn_agregar.setOnClickListener {
            generateDialog()
        }
    }

    private fun generateDialog() {
        val dialogView = layoutInflater
            .inflate(R.layout.add_producto_dialog, null)
        val producto_nombre_Input = dialogView.nombre_producto_input
        val producto_precio_Input = dialogView.precio_producto_input
        val dialogBuilder = AlertDialog
            .Builder(requireContext())
            .setTitle("Agrega un Producto")
            .setView(dialogView)
            .setNegativeButton("Cerrar") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
            .setPositiveButton("Agregar") { dialog: DialogInterface, _: Int ->

                if (producto_nombre_Input.text?.isNotEmpty()!! && producto_precio_Input.text?.isNotEmpty()!!) {

                    AsyncTask.execute {
                        if (producto_nombre_Input != null && producto_precio_Input != null) {
                            dao.insertProducto(
                                insertProducto(
                                    producto_nombre_Input.text.toString(),
                                    producto_precio_Input.text.toString()
                                )
                            )
                        }

                        var thread = Thread() {
                            fun run() {
                                try {
                                    synchronized(this) {
                                        Thread.sleep(500)
                                        UiThreadStatement.runOnUiThread {
                                            //                                                adapter.updateData(newItems)
                                            dialog.dismiss()
                                        }
                                    }
                                } catch (e: InterruptedException) {
                                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                        thread.start()
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Ningún campo debe estar vacío",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        dialogBuilder.create().show()
    }

      fun insertProducto(nombre_prod: String, precio_prod: String): Productos_Entity {
          val producto = Productos_Entity(
              nombre_producto = nombre_prod, precio_producto = precio_prod.toInt()
          )
          return producto
      }

}