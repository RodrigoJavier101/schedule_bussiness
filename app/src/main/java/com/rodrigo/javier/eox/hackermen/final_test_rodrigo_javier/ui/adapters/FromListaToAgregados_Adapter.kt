package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Productos_DataView

class FromListaToAgregados_Adapter(
    private val productos: MutableList<Productos_DataView>
) : RecyclerView.Adapter<FromListaToAgregados_Adapter.ProductoViewHolder>() {

    private var producto: List<Productos_DataView> =
        emptyList<Productos_DataView>()

    inner class ProductoViewHolder(view_: View) :
        RecyclerView.ViewHolder(view_) {

        val producto_item: TextView = view_.findViewById(R.id.lbl_agregar_item_inventario)
        val producto_valor: TextView = view_.findViewById(R.id.lbl_agregar_precio_item_inventario)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FromListaToAgregados_Adapter.ProductoViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_agregar_a_ventas, parent, false)

        return ProductoViewHolder(view)
    }


    override fun onBindViewHolder(
        holder: FromListaToAgregados_Adapter.ProductoViewHolder,
        position: Int
    ) {
        val currente: Productos_DataView = productos[position]
        holder.producto_item.text = currente.nombre_producto
        holder.producto_valor.text = currente.precio_producto.toString()
    }

    override fun getItemCount(): Int {
        return productos.size
    }
}