package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R

class AddNoteFragment(
    private var editTextNombreCliente: EditText? = null,
    private var editTextDomicilioCliente: EditText? = null,
    private var numberPickerTelefonoCliente: NumberPicker? = null,
) : Fragment() {
    private var noteViewModel: RutaViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_cliente, container, false)
        editTextNombreCliente = view.findViewById(R.id.edit_text_nombre_cliente)
        editTextDomicilioCliente = view.findViewById(R.id.edit_text_domicilio_cliente)
        numberPickerTelefonoCliente = view.findViewById(R.id.number_picker_telefono_cliente)
        numberPickerTelefonoCliente?.minValue = 0//setMinValue(1)
        numberPickerTelefonoCliente?.maxValue = 9//setMaxValue(10)

        noteViewModel = ViewModelProvider(this).get(RutaViewModel::class.java)

        val buttonAddNotes = view.findViewById<FloatingActionButton>(R.id.button_add_note)
        buttonAddNotes.setOnClickListener {
            saveNote()
        }

        return view
    }

    /* override fun onOptionsItemSelected(item: MenuItem): Boolean {
         return when (item.itemId) {
             R.id.save_note -> {
                 saveNote()
                 true
             }
             else -> super.onOptionsItemSelected(item)
         }
     }*/

    private fun saveNote() {
        val nombre = editTextNombreCliente!!.text.toString()
        val domicilio = editTextDomicilioCliente!!.text.toString()
        val telefono = numberPickerTelefonoCliente!!.value
        if (nombre.trim { it <= ' ' }.isEmpty() || domicilio.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(context, "Debes agregar nombre y domicilio", Toast.LENGTH_SHORT)
                .show()
            return
        } else if (nombre.trim { it <= ' ' }.isNotEmpty() || domicilio.trim { it <= ' ' }
                .isNotEmpty()) {
            val note =
                Clientes_Entity(nombre!!, domicilio!!, telefono.toLong())
            noteViewModel!!.insert(note)
            Toast.makeText(context, "Note saved", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Not saved", Toast.LENGTH_SHORT).show()
        }
//        val data = Intent()
//        data.putExtra(EXTRA_NOMBRE_CLIENTE, nombre)
//        data.putExtra(EXTRA_DOMICILIO_CLIENTE, domicilio)
//        data.putExtra(EXTRA_TELEFONO_CLIENTE, telefono)
        //        setResult(RESULT_OK, data)
        //        finish()
    }

    companion object {
/*        const val EXTRA_NOMBRE_CLIENTE =
            "com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.EXTRA_NOMBRE_CLIENTE"
        const val EXTRA_DOMICILIO_CLIENTE =
            "com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.EXTRA_DOMICILIO_CLIENTE"
        const val EXTRA_TELEFONO_CLIENTE =
            "com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.EXTRA_TELEFONO_CLIENTE"

        const val ADD_NOTE_REQUEST = 1*/

        fun newInstance(): AddNoteFragment {
            var newaddNoteFragment_ = AddNoteFragment()
            return newaddNoteFragment_
        }

    }
}

