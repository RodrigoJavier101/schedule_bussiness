package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Uno_


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R

class RutaAdapter(
    private var data: LiveData<MutableList<Clientes_Entity>>?,
    private val listenerUpdateCliente: ItemClienteClickListener,
    val context: Context
) :
    RecyclerView.Adapter<RutaAdapter.Holder>() {
    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        private var nombreCliente: TextView
        private var domicilioCliente: TextView
        private var telefonoCliente: TextView


        init {
            nombreCliente = itemView!!.findViewById(R.id.lbl_item_nombre_cliene_ruta)
            domicilioCliente = itemView!!.findViewById(R.id.lbl_item_domicilio_ruta)
            telefonoCliente = itemView!!.findViewById(R.id.lbl_item_telefono_ruta)
        }

        fun bindItems(
            cliente: Clientes_Entity,
            listener: ItemClienteClickListener
        ) {
            nombreCliente = itemView!!.findViewById(R.id.lbl_item_nombre_cliene_ruta)
            domicilioCliente = itemView!!.findViewById(R.id.lbl_item_domicilio_ruta)
            telefonoCliente = itemView!!.findViewById(R.id.lbl_item_telefono_ruta)

            nombreCliente.text = cliente.nombre_cliente
            domicilioCliente.text = cliente.domicilio_cliente
            telefonoCliente.text = cliente.domicilio_cliente

            itemView.setOnClickListener {
                listenerUpdateCliente.itemClienteUpdateClick(cliente)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_ruta, parent, false)
        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindItems(data!!.value!!.get(position), this.listenerUpdateCliente)
    }

    override fun getItemCount(): Int = data?.value?.size ?: 0
//    override fun getItemCount(): Int = data!!.size


    fun addItems(filaCliente: LiveData<MutableList<Clientes_Entity>>?) {
        data = filaCliente
        notifyDataSetChanged()
    }
}






