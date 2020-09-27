package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_admin.fragmentos_interiores.admin_clientes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Clientes_Entity


class AdminClientesFragment : Fragment() {

    private var clientesViewModel: AdminClientesViewModel? = null
    private var adapter: AdminClientesAdapter? = null

    private var nombreCliente: TextView? = null
    private var domicilioCliente: TextView? = null
    private var telefonoCliente: TextView? = null

    private var editTextNombreCliente: EditText? = null
    private var editTextDomicilioCliente: EditText? = null
    private var editTextTelefonoCliente: EditText? = null

    private lateinit var btnAgregarCliente: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_admin_clientes, container, false)

        nombreCliente = view.findViewById(R.id.lbl_item_nombre_cliente)
        domicilioCliente = view.findViewById(R.id.lbl_item_domicilio_cliente)
        telefonoCliente = view.findViewById(R.id.lbl_item_domicilio_cliente)

        editTextNombreCliente = view.findViewById(R.id.cliente__input)
        editTextDomicilioCliente = view.findViewById(R.id.domicilio_cliente_input)
        editTextTelefonoCliente = view.findViewById(R.id.telefono_cliente_input)

        btnAgregarCliente = view.findViewById<Button>(R.id.btn_agregar_cliente)
        btnAgregarCliente.setOnClickListener {
            guardarCliente()
        }

        val recyclerClientes = view.findViewById<RecyclerView>(R.id.recycler_clientes)
        recyclerClientes.layoutManager = LinearLayoutManager(context)
        recyclerClientes.setHasFixedSize(true)
        adapter = AdminClientesAdapter()
        recyclerClientes.adapter = adapter

        clientesViewModel = ViewModelProvider(this).get(AdminClientesViewModel::class.java)
        clientesViewModel!!.allClientes!!.observe(viewLifecycleOwner, { clientes ->
            Log.d("-----------LOG------------->", clientes.toString())
            adapter!!.setClientes(clientes)
        })
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                clientesViewModel!!.deleteCliente(adapter!!.getClienteAt(viewHolder.adapterPosition))
                Toast.makeText(context, "Proveedor eliminado de DDBB", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerClientes)

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Toast.makeText(context, "Cliente a la Izquierda", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerClientes)

        return view
    }

    private fun guardarCliente() {
        val nombre = editTextNombreCliente!!.text.toString()
        val domicilio = editTextDomicilioCliente!!.text.toString()
        val telefono = editTextTelefonoCliente!!.text.toString()
        if (nombre.trim { it <= ' ' }.isEmpty() || domicilio.trim { it <= ' ' }
                .isEmpty() || telefono.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(
                context,
                "Debes agregar nombre, domicilio y teléfono",
                Toast.LENGTH_SHORT
            )
                .show()
            return
        } else if (nombre.trim { it <= ' ' }.isNotBlank() || domicilio.trim { it <= ' ' }
                .isNotBlank() || telefono.trim { it <= ' ' }.isNotBlank()) {
            val cliente =
                Clientes_Entity(nombre!!, domicilio!!, telefono.toLong())
            Log.d("-----------LOG------------->", cliente.domicilio_cliente.toString())
            clientesViewModel!!.insertCliente(cliente)
            Toast.makeText(context, "Cliente guardao en DDBB", Toast.LENGTH_SHORT).show()
            limpiarViews()
            /*aqui me gustaria que se cambien automaticamente a la lista de la ruta*/
        } else {
            Toast.makeText(context, "No guardado", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance(): AdminClientesFragment {
            var newAdminClientesFragment = AdminClientesFragment()
            return newAdminClientesFragment
        }
    }

    private fun limpiarViews() {
        editTextNombreCliente!!.setText("")
        editTextDomicilioCliente!!.setText("")
        editTextTelefonoCliente!!.setText("")
    }
}
