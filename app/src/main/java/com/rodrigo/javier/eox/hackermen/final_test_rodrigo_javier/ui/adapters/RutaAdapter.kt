package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.interfaces.ItemClienteClickListener
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Clientes_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionDatabase
import kotlinx.android.synthetic.main.fragment_admin_clientes.view.*
import kotlinx.android.synthetic.main.item_ruta.view.*

class RutaAdapter(
    private var data: LiveData<List<Clientes_Entity>>,
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

        /*     fun initialize(
                 item_cliente: Clientes_Entity,
                 listener: ItemClienteClickListener
             ) {
                 itemView.setOnClickListener {
                     listenerUpdateCliente.itemClienteUpdateClick(item_cliente)
                 }
             }
     */

        fun bindItems(cliente: LiveData<List<Clientes_Entity>>, listener: ItemClienteClickListener) {
            nombreCliente = itemView!!.findViewById(R.id.lbl_item_nombre_cliene_ruta)
            domicilioCliente = itemView!!.findViewById(R.id.lbl_item_domicilio_ruta)
            telefonoCliente = itemView!!.findViewById(R.id.lbl_item_telefono_ruta)


            nombreCliente.text = cliente.value?.get(position)!!.nombre_cliente
            domicilioCliente.text = cliente.value?.get(position)!!.domicilio_cliente
            telefonoCliente.text = cliente.value?.get(position)!!.domicilio_cliente

            itemView.setOnClickListener {
                listenerUpdateCliente.itemClienteUpdateClick(cliente.value?.get(position)!!)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutaAdapter.Holder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_ruta, parent, false)
        return Holder(v)
    }

    override fun onBindViewHolder(holder: RutaAdapter.Holder, position: Int) {
        holder?.bindItems(data, this.listenerUpdateCliente)

/*        val data: Clientes_Entity = data!![position]
        (holder).itemView.lbl_item_nombre_cliene_ruta.setText(data.nombre_cliente)
        (holder).itemView.lbl_item_domicilio_ruta.setText(data.domicilio_cliente)
        (holder).itemView.lbl_item_telefono_ruta.setText(data.telefono_cliente.toString())

        holder.initialize(
            data.get(position),
            listenerUpdateCliente
        )*/
    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }

    fun addItems(filaCliente: LiveData<List<Clientes_Entity>>) {
        data = filaCliente
        notifyDataSetChanged()
    }
}






