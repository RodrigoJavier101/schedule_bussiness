package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R

class AddNoteFragment(
    private var editTextNombreCliente: EditText? = null,
    private var editTextDomicilioCliente: EditText? = null,
    private var editTextTelefonoCliente: EditText? = null,
) : Fragment() {
    private var noteViewModel: RutaViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_cliente, container, false)
        editTextNombreCliente = view.findViewById(R.id.edit_text_nombre_client)
        editTextDomicilioCliente = view.findViewById(R.id.edit_text_domicilio_client)
        editTextTelefonoCliente = view.findViewById(R.id.edit_text_view_telefon)

        noteViewModel = ViewModelProvider(this).get(RutaViewModel::class.java)

        val buttonAddNotes = view.findViewById<FloatingActionButton>(R.id.button_add_note)
        buttonAddNotes.setOnClickListener {
            saveNote()
        }

        return view
    }

    private fun saveNote() {
        val nombre = editTextNombreCliente!!.text.toString()
        val domicilio = editTextDomicilioCliente!!.text.toString()
        val telefono = editTextTelefonoCliente!!.text.toString()
        if (nombre.trim { it <= ' ' }.isEmpty() || domicilio.trim { it <= ' ' }
                .isEmpty() || telefono.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(
                context,
                "Debes agregar nombre, domicilio y teléfono",
                Toast.LENGTH_SHORT
            )
                .show()
            return
        } else if (nombre.trim { it <= ' ' }.isNotEmpty() || domicilio.trim { it <= ' ' }
                .isNotEmpty() || telefono.trim { it <= ' ' }.isNotEmpty()) {
            val cliente =
                Clientes_Entity(nombre!!, domicilio!!, telefono.toLong())
            noteViewModel!!.insert(cliente)
            Toast.makeText(context, "Cliente guardao en DDBB", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "No guardado", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance(): AddNoteFragment {
            var newaddNoteFragment_ = AddNoteFragment()
            return newaddNoteFragment_
        }
    }
}

