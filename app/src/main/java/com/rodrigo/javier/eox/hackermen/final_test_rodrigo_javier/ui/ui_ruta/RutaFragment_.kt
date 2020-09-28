package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ruta


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R

class RutaFragment_ : Fragment() {
    private var rutaViewModel: RutaViewModel? = null
    private var adapter: RutaAdapter? = null

    private var nombreClienteRuta: TextView? = null
    private var domicilioClienteRuta: TextView? = null
    private var telefonoClienteRuta: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_ruta_new, container, false)

        nombreClienteRuta = view.findViewById(R.id.lbl_item_nombre_cliene_ruta)
        domicilioClienteRuta = view.findViewById(R.id.lbl_item_domicilio_ruta)
        telefonoClienteRuta = view.findViewById(R.id.lbl_item_telefono_ruta)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        adapter = RutaAdapter()
        recyclerView.adapter = adapter
        rutaViewModel = ViewModelProvider(this).get(RutaViewModel::class.java)
        rutaViewModel!!.allClientes!!.observe(viewLifecycleOwner, { clientes ->
            Log.d("-----------LOG------------->", clientes.toString())
            adapter!!.setClientes(clientes)
        })
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: ViewHolder,
                target: ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                rutaViewModel!!.delete(adapter!!.getClienteAt(viewHolder.adapterPosition))
                Toast.makeText(context, "Cliente eliminado de DDBB", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)
        return view
    }

    companion object {
        fun newInstance(): RutaFragment_ {
            var newRutaFragment_ = RutaFragment_()
            return newRutaFragment_
        }
    }
}