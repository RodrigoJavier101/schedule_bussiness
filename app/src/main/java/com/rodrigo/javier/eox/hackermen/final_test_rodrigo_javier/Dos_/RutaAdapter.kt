package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_.RutaAdapter.NoteHolder
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import java.util.*

class RutaAdapter(private var clientes: List<Clientes_Entity>? = ArrayList()) :
    RecyclerView.Adapter<NoteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.ruta_item,
            parent, false
        )
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentClien = clientes!![position]
        holder.textViewTitleNombreCliente.text = currentClien.nombre_cliente
        holder.textViewDomicilioCliente.text = currentClien.domicilio_cliente
        holder.textViewTelefonoCliente.text = currentClien.telefono_cliente.toString()
    }

    override fun getItemCount(): Int {
        return clientes!!.size
    }

    fun setNotes(notes: List<Clientes_Entity>?) {
        this.clientes = notes
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): Clientes_Entity? {
        return clientes!![position]
    }

    inner class NoteHolder(itemView: View) : ViewHolder(itemView) {
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