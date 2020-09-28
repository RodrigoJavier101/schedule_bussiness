package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ventas_gastos.ventas.fragment_interiores.lista_inventario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Productos_Entity
import java.util.ArrayList

class Lista_Inventario_Adapter(
    private var productos: List<Productos_Entity>? = ArrayList()
) :
    RecyclerView.Adapter<Lista_Inventario_Adapter.ProductoViewHolder>() {

    inner class ProductoViewHolder(var item_view: View) : RecyclerView.ViewHolder(item_view) {

        val textViewId: TextView
        val textViewNombreProducto: TextView
        val textViewPrecioProducto: TextView

        init {
            textViewId = item_view.findViewById(R.id.lbl_id_item_listado)
            textViewNombreProducto = item_view.findViewById(R.id.lbl_item_inventario)
            textViewPrecioProducto = item_view.findViewById(R.id.lbl_precio_item_inventario)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        item: Int
    ): ProductoViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_listado_inventario, parent, false)

        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProductoViewHolder,
        position: Int
    ) {
        val dataProductos: Productos_Entity = productos!! [position]
        holder.textViewId.text = dataProductos.id_producto.toString()
        holder.textViewNombreProducto.text = dataProductos.nombre_producto
        holder.textViewPrecioProducto.text = dataProductos.precio_producto.toString()

    }

    override fun getItemCount(): Int {
        return productos!!.size
    }

    fun setProductos(productos: List<Productos_Entity>?) {
        this.productos = productos
        notifyDataSetChanged()
    }

    fun getProductoAt(position: Int): Productos_Entity? {
        return productos!![position]
    }

}
