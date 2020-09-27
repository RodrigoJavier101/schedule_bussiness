package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ventas_gastos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Productos_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.interfaces.CardViewListenerLongClick
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.interfaces.CardViewListenerShortClick
import kotlinx.android.synthetic.main.item_listado_inventario.view.*

class Lista_Inventario_Adapter(
    private val lista_productos: List<Productos_Entity>
//    ,
//    private var listenerCardViewShort: CardViewListenerShortClick,
//    private var listenerCardViewLong: CardViewListenerLongClick
    ) :
    RecyclerView.Adapter<Lista_Inventario_Adapter.ProductoViewHolder>() {

    inner class ProductoViewHolder(var item_view: View) : RecyclerView.ViewHolder(item_view) {

        var lbl_id_item: TextView
        var lbl_item_nombre: TextView
        var lbl_precio_item: TextView
        var cardView: CardView

        init {
            lbl_id_item = item_view.findViewById(R.id.lbl_id_item_listado)
            lbl_item_nombre =
                item_view.findViewById(R.id.lbl_item_inventario) as TextView
            lbl_precio_item =
                item_view.findViewById(R.id.lbl_precio_item_inventario) as TextView
            cardView = item_view.findViewById(R.id.cardview_item_inventario)
        }

        fun initialize(
            item: Productos_Entity,
            actionShort: CardViewListenerShortClick,
            actionLong: CardViewListenerLongClick
        ) {
            item_view.setOnClickListener {
                /*listenerCardViewShort.cardViewClickedShort(
                    producto = lista_productos.get(this.layoutPosition),
                    item_view.cardview_item_inventario,
                    this.layoutPosition
                )*/
            }

            item_view.setOnLongClickListener {
                /*listenerCardViewLong.cardViewClickedLong(
                    producto = lista_productos.get(this.layoutPosition)
                )*/
                return@setOnLongClickListener true
            }
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
        val data: Productos_Entity = lista_productos[position]
        (holder).item_view.lbl_id_item_listado.setText(data.id_producto.toString())
        (holder).item_view.lbl_item_inventario.setText(data.nombre_producto)
        (holder).item_view.lbl_precio_item_inventario.setText(data.precio_producto.toString())
        /*holder.initialize(
            lista_productos.get(position),
            listenerCardViewShort,
            listenerCardViewLong
        )*/

    }

    override fun getItemCount(): Int {
        return lista_productos.size
    }

}
