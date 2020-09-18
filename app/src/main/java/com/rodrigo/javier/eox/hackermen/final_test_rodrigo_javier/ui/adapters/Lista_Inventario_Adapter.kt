package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.interfaces.CardViewListener
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Productos_Entity
import kotlinx.android.synthetic.main.item_listado_inventario.view.*

class Lista_Inventario_Adapter(
    private val lista_productos: List<Productos_Entity>,
    private var listenerCardView: CardViewListener
) :
    RecyclerView.Adapter<Lista_Inventario_Adapter.ProductoViewHolder>() {

//    private var checkItem = emptyList<Productos_Entity>()

    inner class ProductoViewHolder(var item_view: View) : RecyclerView.ViewHolder(item_view) {

        var lbl_item_inventario: TextView
        var lbl_precio_item_inventario: TextView
        var cardView: CardView

        init {
            lbl_item_inventario =
                item_view.findViewById(R.id.lbl_item_inventario) as TextView
            lbl_precio_item_inventario =
                item_view.findViewById(R.id.lbl_precio_item_inventario) as TextView
            cardView = item_view.findViewById(R.id.cardview_item_inventario)

        }

        fun initialize(item: Productos_Entity, action: CardViewListener) {
            item_view.setOnClickListener {
                listenerCardView.cardViewClicked(
                    item_view.cardview_item_inventario,
                    this.layoutPosition
                )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        item: Int
    ): Lista_Inventario_Adapter.ProductoViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_listado_inventario, parent, false)

        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: Lista_Inventario_Adapter.ProductoViewHolder,
        position: Int
    ) {
        val data: Productos_Entity = lista_productos[position]
        (holder).item_view.lbl_item_inventario.setText(data.nombre_producto)
        (holder).item_view.lbl_precio_item_inventario.setText(data.precio_producto.toString())
        holder.initialize(lista_productos.get(position), listenerCardView)

    }

    override fun getItemCount(): Int {
        return lista_productos.size
    }

}
