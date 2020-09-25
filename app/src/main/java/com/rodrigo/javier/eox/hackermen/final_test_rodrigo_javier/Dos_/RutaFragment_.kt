package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R

class RutaFragment_ : Fragment() {
    private var noteViewModel: RutaViewModel? = null
    private var adapter: RutaAdapter? = null

    private lateinit var nombreCliente: TextView
    private lateinit var domicilioCliente: TextView
    private lateinit var telefonoCliente: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_ruta_new, container, false)

        nombreCliente = view.findViewById(R.id.cliente_ruta_input)
        domicilioCliente = view.findViewById(R.id.domicilio_cliente_ruta_input)
        telefonoCliente = view.findViewById(R.id.telefono_cliente_ruta_input)

        val buttonAddNotes = view.findViewById<FloatingActionButton>(R.id.button_add_note)
        buttonAddNotes.setOnClickListener {
            val intent = Intent(context, AddNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        /*es por si cambia el tamaño del rv*/recyclerView.setHasFixedSize(true)
        adapter = RutaAdapter()
        /*por default el adapter está vacío*/recyclerView.adapter = adapter
        /*hay que attach el observer al livedata*/
        /*view model se destruye cuando no se necesita*/
//        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel = ViewModelProvider(this).get(RutaViewModel::class.java)
        noteViewModel!!.allNotes!!.observe(viewLifecycleOwner, { notes ->

            /*aquí es donde se actualiza el adpater */
            /*por el momento la lista no me llega desde la ddbb no se porqué*/
//            var cliente1 = Clientes_Entity("juanita perez EXTRA", "jotaperez@gmail.com")

            adapter!!.setNotes(notes)
        })
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: ViewHolder,
                target: ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                noteViewModel!!.delete(adapter!!.getNoteAt(viewHolder.adapterPosition))
                Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)


        return view
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            val nombreCliente = data!!.getStringExtra(AddNoteActivity.EXTRA_NOMBRE_CLIENTE)
            val domicilioCliente = data.getStringExtra(AddNoteActivity.EXTRA_DOMICILIO_CLIENTE)
            val telefonoClientes = data.getIntExtra(AddNoteActivity.EXTRA_TELEFONO_CLIENTE, 111111111)
            val note = Clientes_Entity(nombreCliente!!, domicilioCliente!!, telefonoClientes.toLong())
            noteViewModel!!.insert(note)
            Toast.makeText(context, "Note saved", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Not saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        return super.onCreateOptionsMenu(menu);
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu_new, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_notes -> {
                noteViewModel!!.deleteAllNotes()
                Toast.makeText(context, "All notes deleted", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val ADD_NOTE_REQUEST = 1

        fun newInstance(): RutaFragment {
            var newRutaFragment = RutaFragment()
            return newRutaFragment
        }
    }
}