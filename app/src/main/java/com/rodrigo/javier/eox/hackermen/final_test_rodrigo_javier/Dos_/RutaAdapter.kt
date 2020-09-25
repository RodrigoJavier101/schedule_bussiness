package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_.RutaAdapter.NoteHolder
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import java.util.*

class RutaAdapter(private var notes: List<Clientes_Entity>? = ArrayList()) :
    RecyclerView.Adapter<NoteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_item,
            parent, false
        )
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote = notes!![position]
        holder.textViewTitle.text = currentNote.title
        holder.textViewDescription.text = currentNote.description
        holder.textViewPriority.text = currentNote.priority.toString()
    }

    override fun getItemCount(): Int {
        return notes!!.size
    }

    fun setNotes(notes: List<Clientes_Entity>?) {
        this.notes = notes
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): Clientes_Entity? {
        return notes!![position]
    }

    inner class NoteHolder(itemView: View) : ViewHolder(itemView) {
        val textViewTitle: TextView
        val textViewDescription: TextView
        val textViewPriority: TextView

        init {
            textViewTitle = itemView.findViewById(R.id.text_view_title)
            textViewDescription = itemView.findViewById(R.id.text_view_description)
            textViewPriority = itemView.findViewById(R.id.text_view_piority)
        }
    }
}