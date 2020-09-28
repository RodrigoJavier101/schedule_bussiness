package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ventas_gastos.ventas.fragment_interiores.agregar_a_ventas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.GestionDao
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.GestionDatabase
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Productos_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Ventas_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ventas_gastos.ventas.fragment_interiores.lista_inventario.ListaViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AgregarAVentasFragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: FromListaToAgregados_Adapter
    private lateinit var fabAg_: FloatingActionButton
    private lateinit var dao: GestionDao
    private lateinit var lista_productos: ArrayList<Productos_Entity>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_agregar_a_ventas, container, false)
        fabAg_ = view.findViewById(R.id.fab_agregar)
//        dataBase = RoomApplication.gestionDatabase!!
        dao = GestionDatabase.getInstance(requireContext())!!.setDao()


        recycler = view.findViewById(R.id.recyclerview_agregados_a_ventas)
        recycler.hasFixedSize()
        recycler.layoutManager = LinearLayoutManager(requireContext())
        lista_productos = arrayListOf()

        return view
    }

    companion object {
        fun newInstance(): AgregarAVentasFragment {
            var newAgregarAVentas_Fragment = AgregarAVentasFragment()

            return newAgregarAVentas_Fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: ListaViewModel =
            ViewModelProvider(requireActivity()).get(ListaViewModel::class.java)
        var count: Int = 0
        viewModel.getProductSelected()?.observe(viewLifecycleOwner) { item ->
            lista_productos.add(item)
            adapter = FromListaToAgregados_Adapter(lista_productos)
            recycler.adapter = adapter
        }
        setUpAddButton(lista_productos)
    }

    private fun setUpAddButton(lista: ArrayList<Productos_Entity>) {
        var counter: Int = 0
        fabAg_.setOnClickListener {
            if (!lista.isNullOrEmpty()) {
                counter = 0
                lista.forEach {
                    counter += it.precio_producto
                }

                CoroutineScope(Dispatchers.IO).launch {
                    var venta: Ventas_Entity = Ventas_Entity(counter)
                    try {
                        dao.insertVenta(venta)
                        Log.d("-----------LOG------------->", "inserted???? a ventas????")
                    } catch (e: Exception) {
                        Log.d("----------LOOOg------------->", e.message.toString())
                    }
                }
                Toast.makeText(
                    context,
                    "Venta por ${counter}!!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(context, "Nada en lista", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
