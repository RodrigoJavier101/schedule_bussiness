package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ventas_gastos.ventas.fragment_interiores.agregar_a_ventas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Productos_Entity
import kotlinx.android.synthetic.main.item_agregar_a_ventas.view.*

class FromListaToAgregados_Adapter(
    private val productos: ArrayList<Productos_Entity>
) : RecyclerView.Adapter<FromListaToAgregados_Adapter.AgregarViewHolder>() {

    inner class AgregarViewHolder(view_: View) :
        RecyclerView.ViewHolder(view_) {

        var lbl_id_producto: TextView
        var lbl_nombre_producto: TextView
        var lbl_precio_producto: TextView

        init {
            lbl_id_producto = view_.lbl_agregar_id_inventario
            lbl_nombre_producto = view_.lbl_agregar_item_inventario
            lbl_precio_producto = view_.lbl_agregar_precio_item_inventario

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
        val data: Productos_Entity = productos[position]
        holder.lbl_id_producto.text = data.id_producto.toString()
        holder.lbl_nombre_producto.text = data.nombre_producto
        holder.lbl_precio_producto.text = data.precio_producto.toString()
    }

    override fun getItemCount(): Int {
        return productos.size
    }
}