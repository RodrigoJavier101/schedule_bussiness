package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.adapters

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.data_view.Productos_DataView
import kotlinx.android.synthetic.main.item_admin_proveedores.view.*
import kotlinx.android.synthetic.main.item_listado_inventario.view.*

class ListadoInventarioAdapter(var lista_productos: ArrayList<Productos_DataView>) :
    RecyclerView.Adapter<ListadoInventarioAdapter.ViewHolder>() {

    class ViewHolder(var item_view: View) : RecyclerView.ViewHolder(item_view) {
        fun bindItems(item: Productos_DataView) {
            item_view.lbl_item_inventario.text = item.nombre_producto
            item_view.lbl_precio_item_inventario.text = item.precio_producto.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListadoInventarioAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_listado_inventario, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListadoInventarioAdapter.ViewHolder, position: Int) {
        holder.bindItems(lista_productos[position])
    }

    override fun getItemCount(): Int {
        return lista_productos.size
    }


}