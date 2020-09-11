package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.entities.Proveedores_Entity
import kotlinx.android.synthetic.main.item_admin_proveedores.view.*

class AdminProveedoresRecyclerAdapter(
    private var lista_proveeodres: MutableList<Proveedores_Entity>,
    context: Context
) :
    RecyclerView.Adapter<ProveedoresViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProveedoresViewHolder {
        return ProveedoresViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_admin_proveedores, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ProveedoresViewHolder, position: Int) {
        TODO("Not yet implemented")
        var data = lista_proveeodres[position]
        holder.lista.text = data.nombre_proveedor
    }


    override fun getItemCount(): Int {
        return lista_proveeodres.size
    }

}

class ProveedoresViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var lista = view.lbl_item_proveedores
}

