package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ruta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Clientes_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ruta.RutaAdapter.RutaHolder
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import java.util.*

class RutaAdapter(private var clientes: List<Clientes_Entity>? = ArrayList()) :
    RecyclerView.Adapter<RutaHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutaHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.ruta_item,
            parent, false
        )
        return RutaHolder(itemView)
    }

    override fun onBindViewHolder(holder: RutaHolder, position: Int) {
        val dataCliente = clientes!![position]
        holder.textViewTitleNombreCliente.text = dataCliente.nombre_cliente
        holder.textViewDomicilioCliente.text = dataCliente.domicilio_cliente
        holder.textViewTelefonoCliente.text = dataCliente.telefono_cliente.toString()
    }

    override fun getItemCount(): Int {
        return clientes!!.size
    }

    fun setClientes(notes: List<Clientes_Entity>?) {
        this.clientes = notes
        notifyDataSetChanged()
    }

    fun getClienteAt(position: Int): Clientes_Entity? {
        return clientes!![position]
    }

    inner class RutaHolder(itemView: View) : ViewHolder(itemView) {
        val textViewTitleNombreCliente: TextView
        val textViewDomicilioCliente: TextView
        val textViewTelefonoCliente: TextView

        init {
            textViewTitleNombreCliente = itemView.findViewById(R.id.lbl_item_nombre_cliene_ruta)
            textViewDomicilioCliente = itemView.findViewById(R.id.lbl_item_domicilio_ruta)
            textViewTelefonoCliente = itemView.findViewById(R.id.lbl_item_telefono_ruta)
        }
    }
}