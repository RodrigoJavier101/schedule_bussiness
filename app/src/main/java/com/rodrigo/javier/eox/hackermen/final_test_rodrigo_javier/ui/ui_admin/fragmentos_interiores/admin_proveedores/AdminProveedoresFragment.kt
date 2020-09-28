package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_admin.fragmentos_interiores.admin_proveedores

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
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Proveedores_Entity


class AdminProveedoresFragment : Fragment() {

    private var proveedoresViewModel: AdminProveedoresViewModel? = null
    private var adapter: AdminProveedoresAdapter? = null

    /*esta es la parte en la que se lee la lista*/
    private var nombreProveedor: TextView? = null
    private var domicilioProveedor: TextView? = null
    private var telefonoProveedor: TextView? = null

    private var editTextNombreProveedor: EditText? = null
    private var editTextDomicilioProveedor: EditText? = null
    private var editTextTelefonoProveedor: EditText? = null

    private lateinit var btnAgregarProveedor: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_admin_proveedores, container, false)

        nombreProveedor = view.findViewById(R.id.lbl_item_nombre_proveedor)
        domicilioProveedor = view.findViewById(R.id.lbl_item_domicilio_proveedor)
        telefonoProveedor = view.findViewById(R.id.lbl_item_telefono_proveedor)

        editTextNombreProveedor = view.findViewById(R.id.nombre_proveedor_input)
        editTextDomicilioProveedor = view.findViewById(R.id.domicilio_proveedor_input)
        editTextTelefonoProveedor = view.findViewById(R.id.telefono_proveedor_input)

        btnAgregarProveedor = view.findViewById<Button>(R.id.btn_agregar_proveedor)
        btnAgregarProveedor.setOnClickListener {
            guardarProveedor()
        }

        val recyclerProveedores = view.findViewById<RecyclerView>(R.id.recycler_provedores)
        recyclerProveedores.layoutManager = LinearLayoutManager(context)
        recyclerProveedores.setHasFixedSize(true)
        adapter = AdminProveedoresAdapter()
        recyclerProveedores.adapter = adapter

        proveedoresViewModel = ViewModelProvider(this).get(AdminProveedoresViewModel::class.java)
        proveedoresViewModel!!.allProveedores!!.observe(viewLifecycleOwner, { proveedores ->
            Log.d("-----------LOG------------->", proveedores.toString())
            adapter!!.setProveedores(proveedores)
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
                proveedoresViewModel!!.deleteProveedor(adapter!!.getProveedorAt(viewHolder.adapterPosition))
                Toast.makeText(context, "Proveedor eliminado de DDBB", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerProveedores)

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
                Toast.makeText(context, "Proveedor a la Izquierda", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerProveedores)

        return view
    }

    private fun guardarProveedor() {
        val nombre = editTextNombreProveedor!!.text.toString()
        val domicilio = editTextDomicilioProveedor!!.text.toString()
        val telefono = editTextTelefonoProveedor!!.text.toString()
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
            val proveedor =
                Proveedores_Entity(nombre!!, domicilio!!, telefono.toLong())
            Log.d("-----------LOG------------->", proveedor.domicilio_proveedor.toString())
            proveedoresViewModel!!.insertProveedor(proveedor)
            Toast.makeText(context, "Cliente guardao en DDBB", Toast.LENGTH_SHORT).show()
            limpiarViews()
            /*aqui me gustaria que se cambien automaticamente a la lista de la ruta*/
        } else {
            Toast.makeText(context, "No guardado", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance(): AdminProveedoresFragment {
            var newAdminProveedoresFragment = AdminProveedoresFragment()
            return newAdminProveedoresFragment
        }
    }

    private fun limpiarViews() {
        editTextNombreProveedor!!.setText("")
        editTextDomicilioProveedor!!.setText("")
        editTextTelefonoProveedor!!.setText("")
    }
}
