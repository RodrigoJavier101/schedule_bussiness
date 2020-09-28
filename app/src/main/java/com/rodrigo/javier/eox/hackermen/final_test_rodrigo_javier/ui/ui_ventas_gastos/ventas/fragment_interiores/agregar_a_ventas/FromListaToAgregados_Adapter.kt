package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ventas_gastos.ventas.fragment_interiores.agregar_a_ventas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Clientes_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Productos_Entity
import kotlinx.android.synthetic.main.item_agregar_a_ventas.view.*

class FromListaToAgregados_Adapter(
    private var productos: List<Productos_Entity>?
) : RecyclerView.Adapter<FromListaToAgregados_Adapter.AgregarViewHolder>() {

    inner class AgregarViewHolder(view_: View) :
        RecyclerView.ViewHolder(view_) {

        var idProducto: TextView
        var nombreProducto: TextView
        var precioProducto: TextView

        init {
            idProducto = view_.lbl_agregar_id_inventario
            nombreProducto = view_.lbl_agregar_item_inventario
            precioProducto = view_.lbl_agregar_precio_item_inventario
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AgregarViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_agregar_a_ventas, parent, false)

        return AgregarViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: AgregarViewHolder,
        position: Int
    ) {
        val data: Productos_Entity = productos!![position]
        holder.idProducto.text = data.id_producto.toString()
        holder.nombreProducto.text = data.nombre_producto
        holder.precioProducto.text = data.precio_producto.toString()
    }

    override fun getItemCount(): Int {
        return productos!!.size
    }

    fun setProductos(productos: List<Productos_Entity>?) {
        this.productos = productos
        notifyDataSetChanged()
    }

}