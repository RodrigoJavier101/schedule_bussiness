package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.getbase.floatingactionbutton.FloatingActionButton

class MainActivityNew : AppCompatActivity() {
    private var noteViewModel: NoteViewModel? = null
    private var adapter: NoteAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonAddNotes = findViewById<FloatingActionButton>(R.id.button_add_note)
        buttonAddNotes.setOnClickListener {
            val intent = Intent(this@MainActivityNew, AddNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        /*es por si cambia el tamaño del rv*/recyclerView.setHasFixedSize(true)
        adapter = NoteAdapter()
        /*por default el adapter está vacío*/recyclerView.adapter = adapter
        /*hay que attach el observer al livedata*/
        /*view model se destruye cuando no se necesita*/
//        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel!!.allNotes!!.observe(this, { notes ->

            /*aquí es donde se actualiza el adpater */
            /*por el momento la lista no me llega desde la ddbb no se porqué*/
            var note1 = Note("Title 1", "Description 1", 1)
            var note2 = Note("Title 1", "Description 1", 1)
            var note3 = Note("Title 1", "Description 1", 1)
            notes!!.forEach {
                Toast.makeText(applicationContext, notes.toString(), Toast.LENGTH_SHORT).show()
            }

            /*notes.add(Note("Title 2", "Description 2", 2))
            notes.add(Note("Title 3", "Description 3", 3))*/
            adapter!!.setNotes(notes)
        })
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView,
                                viewHolder: ViewHolder,
                                target: ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                noteViewModel!!.delete(adapter!!.getNoteAt(viewHolder.adapterPosition))
                Toast.makeText(this@MainActivityNew, "Note deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            val title = data!!.getStringExtra(AddNoteActivity.EXTRA_TITLE)
            val description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)
            val priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1)
            val note = Note(title!!, description!!, priority)
            noteViewModel!!.insert(note)
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Not saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        return super.onCreateOptionsMenu(menu);
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_notes -> {
                noteViewModel!!.deleteAllNotes()
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val ADD_NOTE_REQUEST = 1
    }
}