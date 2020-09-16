package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.data_view.Productos_DataView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.data_view.Productos_DataViewHeader
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ventas_gastos.ventas.VentasViewModel
import kotlinx.android.synthetic.main.header_listado_inventario.view.*
import kotlinx.android.synthetic.main.item_listado_inventario.view.*

class Ventas_Invent_Adapter(var lista_productos: ArrayList<Productos_DataView>) :
    RecyclerView.Adapter<Ventas_Invent_Adapter.ItemViewHolder>() {


//    private val TYPE_HEADER: Int = 0
//    private val TYPE_ITEM: Int = 1


    /* inner class HeaderViewHolder(var item_view: View) : RecyclerView.ViewHolder(item_view) {
 //        fun bindItems(item: Productos_DataView) {
 //            item_view.lbl_header_item_inventario.text = item.nombre_producto
 //            item_view.lbl_header_precio_item_inventario.text = item.precio_producto.toString()
 //        }

         var lbl_header_item_inventario: TextView
         var lbl_precio_item_inventario: TextView

         init {
             lbl_header_item_inventario =
                 item_view.findViewById<TextView>(R.id.lbl_header_item_inventario)
             lbl_precio_item_inventario =
                 item_view.findViewById<TextView>(R.id.lbl_header_precio_item_inventario)
         }
     }*/

    inner class ItemViewHolder(var item_view: View) : RecyclerView.ViewHolder(item_view) {
        //        fun bindItems(item: Productos_DataViewHeader) {
//            item_view.lbl_item_inventario.text = item.nombre_producto
//            item_view.lbl_precio_item_inventario.text = item.precio_producto.toString()
//        }
        var lbl_item_inventario: TextView
        var lbl_precio_item_inventario: TextView

        init {
            lbl_item_inventario =
                item_view.findViewById<TextView>(R.id.lbl_item_inventario) as TextView
            lbl_precio_item_inventario =
                item_view.findViewById<TextView>(R.id.lbl_precio_item_inventario) as TextView
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        type_item_or_header: Int
    ): Ventas_Invent_Adapter.ItemViewHolder {

//        if (type_item_or_header == TYPE_HEADER) {
//            val view =
//                LayoutInflater.from(parent.context)
//                    .inflate(R.layout.header_listado_inventario, parent, false)
//            return HeaderViewHolder(view)
//        } else if (type_item_or_header == TYPE_ITEM) {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_listado_inventario, parent, false)

        return ItemViewHolder(view)
//        }
//        throw RuntimeException("No match for $type_item_or_header.")

    }

    override fun onBindViewHolder(holder: Ventas_Invent_Adapter.ItemViewHolder, position: Int) {
//        holder.bindItems(lista_productos[position])
        val data = lista_productos[position]
//        if (holder is HeaderViewHolder) {
//            (holder as HeaderViewHolder).item_view.lbl_header_item_inventario.setText(data.nombre_producto)
//            (holder as HeaderViewHolder).item_view.lbl_header_item_inventario.setText(data.precio_producto)
//        } else if (holder is ItemViewHolder) {
        (holder as ItemViewHolder).item_view.lbl_item_inventario.setText(data.nombre_producto)
        (holder as ItemViewHolder).item_view.lbl_precio_item_inventario.setText(data.precio_producto.toString())

//        }
    }

    override fun getItemCount(): Int {
        return lista_productos.size
    }

    /* override fun getItemViewType(position: Int): Int {
         return if (isPositionHeader(position)) TYPE_HEADER else TYPE_ITEM
     }

     private fun isPositionHeader(position: Int): Boolean {
         return position == 0
     }*/

}
