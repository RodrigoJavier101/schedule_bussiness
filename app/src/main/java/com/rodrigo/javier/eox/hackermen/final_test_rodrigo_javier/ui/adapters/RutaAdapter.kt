package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.interfaces.ItemClienteClickListener
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Clientes_Entity
import kotlinx.android.synthetic.main.item_ruta.view.*


class RutaAdapter(
    private val lista: List<Clientes_Entity>,
    private val listenerUpdateCliente: ItemClienteClickListener
) :
    RecyclerView.Adapter<RutaAdapter.MyViewHolder>() {


    inner class MyViewHolder(var item_view: View) : RecyclerView.ViewHolder(item_view) {
        private var nombreCliente: TextView
        private var domicilioCliente: TextView
        private var telefonoCliente: TextView


        init {
            nombreCliente = item_view.findViewById(R.id.lbl_item_nombre_cliene_ruta)
            domicilioCliente = item_view.findViewById(R.id.lbl_item_domicilio_ruta)
            telefonoCliente = item_view.findViewById(R.id.lbl_item_telefono_ruta)
        }

        fun initialize(
            item_cliente: Clientes_Entity,
            listener: ItemClienteClickListener
        ) {
            item_view.setOnClickListener {
                listenerUpdateCliente.itemClienteUpdateClick(item_cliente)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ruta, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data: Clientes_Entity = lista[position]
        (holder).item_view.lbl_item_nombre_cliene_ruta.setText(data.nombre_cliente)
        (holder).item_view.lbl_item_domicilio_ruta.setText(data.domicilio_cliente)
        (holder).item_view.lbl_item_telefono_ruta.setText(data.telefono_cliente.toString())

        holder.initialize(
            lista.get(position),
            listenerUpdateCliente
        )
    }

    override fun getItemCount(): Int {
        return lista.size
    }

}