package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ruta

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Clientes_Entity

class AddClienteFragment(
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

    private fun limpiarViews() {
        editTextNombreCliente!!.setText("")
        editTextDomicilioCliente!!.setText("")
        editTextTelefonoCliente!!.setText("")

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
        } else if (nombre.trim { it <= ' ' }.isNotBlank() || domicilio.trim { it <= ' ' }
                .isNotBlank() || telefono.trim { it <= ' ' }.isNotBlank()) {
            val cliente =
                Clientes_Entity(nombre!!, domicilio!!, telefono.toLong())
            Log.d("-----------LOG------------->", cliente.domicilio_cliente.toString())
            noteViewModel!!.insert(cliente)
            Toast.makeText(context, "Cliente guardao en DDBB", Toast.LENGTH_SHORT).show()
            limpiarViews()
            /*aqui me gustaria que se cambien automaticamente a la lista de la ruta*/
        } else {
            Toast.makeText(context, "No guardado", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance(): AddClienteFragment {
            var newaddNoteFragment_ = AddClienteFragment()
            return newaddNoteFragment_
        }
    }
}

