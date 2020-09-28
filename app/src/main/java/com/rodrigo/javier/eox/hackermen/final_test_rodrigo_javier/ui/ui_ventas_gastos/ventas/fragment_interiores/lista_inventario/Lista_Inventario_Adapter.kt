package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ventas_gastos.ventas.fragment_interiores.lista_inventario

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
import kotlinx.coroutines.withContext
import java.util.ArrayList
import kotlin.coroutines.coroutineContext

class Lista_Inventario_Adapter(
    private var productos: List<Productos_Entity>? = ArrayList(),
    private var listenerCardViewShort: CardViewListenerShortClick,
    private var listenerCardViewLong: CardViewListenerLongClick
) :
    RecyclerView.Adapter<Lista_Inventario_Adapter.ProductoViewHolder>() {

    inner class ProductoViewHolder(var item_view: View) : RecyclerView.ViewHolder(item_view) {

        val textViewId: TextView
        val textViewNombreProducto: TextView
        val textViewPrecioProducto: TextView
        val carview: CardView

        init {
            textViewId = item_view.findViewById(R.id.lbl_id_item_listado)
            textViewNombreProducto = item_view.findViewById(R.id.lbl_item_inventario)
            textViewPrecioProducto = item_view.findViewById(R.id.lbl_precio_item_inventario)
            carview = item_view.findViewById(R.id.cardview_item_inventario)
        }

        fun initialize(
            item: Productos_Entity,
            actionShort: CardViewListenerShortClick,
            actionLong: CardViewListenerLongClick
        ) {
            item_view.setOnClickListener {
                listenerCardViewShort.cardViewClickedShort(
                    producto = productos!!.get(this.layoutPosition),
                    item_view.cardview_item_inventario,
                    this.layoutPosition
                )
            }

            item_view.setOnLongClickListener {
                listenerCardViewLong.cardViewClickedLong(
                    producto = productos!!.get(this.layoutPosition)
                )
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
        val dataProductos: Productos_Entity = productos!![position]
        holder.textViewId.text = dataProductos.id_producto.toString()
        holder.textViewNombreProducto.text = dataProductos.nombre_producto
        holder.textViewPrecioProducto.text = dataProductos.precio_producto.toString()

        holder.initialize(
            productos!!.get(position),
            listenerCardViewShort,
            listenerCardViewLong
        )

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
