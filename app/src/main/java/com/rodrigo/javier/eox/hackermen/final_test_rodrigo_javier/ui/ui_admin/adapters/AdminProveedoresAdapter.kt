package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_admin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Proveedores_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ruta.RutaAdapter.RutaHolder
import java.util.*


class AdminProveedoresAdapter(
    private var proveedores: List<Proveedores_Entity>? = ArrayList()
) :
    RecyclerView.Adapter<AdminProveedoresAdapter.ProveedoresHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProveedoresHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_admin_proveedores, parent, false)
        return ProveedoresHolder(
            itemView
        )
    }


    override fun onBindViewHolder(holder: ProveedoresHolder, position: Int) {
        var dataProveedores = proveedores!![position]
        holder.textViewTitleNombreProveedor.text = dataProveedores.nombre_proveedor
        holder.textViewDomicilioProveedor.text = dataProveedores.domicilio_proveedor
        holder.textViewTelefonoProveedor.text = dataProveedores.telefono_proveedor.toString()
    }


    override fun getItemCount(): Int {
        return proveedores!!.size
    }

    fun setProveedores(proveedores: List<Proveedores_Entity>?) {
        this.proveedores = proveedores
        notifyDataSetChanged()
    }

    fun getProveedorAt(position: Int): Proveedores_Entity? {
        return proveedores!![position]
    }

    inner class ProveedoresHolder(itemView: View) : ViewHolder(itemView) {
        val textViewTitleNombreProveedor: TextView
        val textViewDomicilioProveedor: TextView
        val textViewTelefonoProveedor: TextView

        init {
            textViewTitleNombreProveedor = itemView.findViewById(R.id.lbl_item_nombre_proveedor)
            textViewDomicilioProveedor = itemView.findViewById(R.id.lbl_item_domicilio_proveedor)
            textViewTelefonoProveedor = itemView.findViewById(R.id.lbl_item_telefono_proveedor)
        }
    }

}



