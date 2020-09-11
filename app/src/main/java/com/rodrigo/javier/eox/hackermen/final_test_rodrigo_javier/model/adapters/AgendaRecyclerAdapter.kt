package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.gestion_data_view.Agenda

class AgendaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val agendaCheck = view.findViewById<TextView>(R.id.recomendation_text)
}


class AgendaRecyclerAdapter(
    private var agendaList: MutableList<Agenda>,
    var context: Context
) : RecyclerView.Adapter<AgendaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaViewHolder {
        return AgendaViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_dialog_agenda, parent, false)
        )
    }


    override fun onBindViewHolder(holder: AgendaViewHolder, position: Int) {
        val data = agendaList[position]
        holder.agendaCheck.text = data.text
    }


    override fun getItemCount(): Int {
        return agendaList.size
    }

    fun upDateData(items: List<Agenda>) {
        agendaList.clear()
        agendaList.addAll(items)
        notifyDataSetChanged()
    }

}