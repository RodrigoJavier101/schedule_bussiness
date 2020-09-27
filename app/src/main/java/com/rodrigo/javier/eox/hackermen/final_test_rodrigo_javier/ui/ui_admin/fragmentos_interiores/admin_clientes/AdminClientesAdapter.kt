package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_admin.fragmentos_interiores.admin_clientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Clientes_Entity
import java.util.ArrayList

class AdminClientesAdapter (
    private var clientes: List<Clientes_Entity>? = ArrayList()
) :
    RecyclerView.Adapter<AdminClientesAdapter.ClientesHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientesHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_admin_clientes, parent, false)
        return ClientesHolder(
            itemView
        )
    }


    override fun onBindViewHolder(holder: ClientesHolder, position: Int) {
        var dataClientes = clientes!![position]
        holder.textViewTitleNombreCliente.text = dataClientes.nombre_cliente
        holder.textViewDomicilioCliente.text = dataClientes.domicilio_cliente
        holder.textViewTelefonoCliente.text = dataClientes.telefono_cliente.toString()
    }


    override fun getItemCount(): Int {
        return clientes!!.size
    }

    fun setClientes(clientes: List<Clientes_Entity>?) {
        this.clientes = clientes
        notifyDataSetChanged()
    }

    fun getClienteAt(position: Int): Clientes_Entity? {
        return clientes!![position]
    }

    inner class ClientesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitleNombreCliente: TextView
        val textViewDomicilioCliente: TextView
        val textViewTelefonoCliente: TextView

        init {
            textViewTitleNombreCliente = itemView.findViewById(R.id.lbl_item_nombre_cliente)
            textViewDomicilioCliente = itemView.findViewById(R.id.lbl_item_domicilio_cliente)
            textViewTelefonoCliente = itemView.findViewById(R.id.lbl_item_telefono_cliente)
        }
    }

}
