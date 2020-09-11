package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.gestion_data_view.Agenda_DataView

class AgendaRecyclerAdapter(
    private var agenda_list: MutableList<Agenda_DataView>,
    var context: Context
) : RecyclerView.Adapter<AgendaRecyclerAdapter.AgendaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : AgendaViewHolder {
        return AgendaViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.recommendation_item_fake, parent, false)
        )
    }

    override fun getItemCount(): Int = agenda_list.size

    override fun onBindViewHolder(
        holder: AgendaViewHolder, position:
        Int
    ) {
        val data = agenda_list[position]
        holder.agenda_box.text = data.asunto_agenda
    }

    fun updateData(items: MutableList<Agenda_DataView>) {
        agenda_list.clear()
        agenda_list.addAll(items)
        notifyDataSetChanged()
    }

    class AgendaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val agenda_box = view.findViewById<TextView>(R.id.agenda_Box)
    }
}

