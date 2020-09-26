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
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R

class AddNoteFragment(
    private var editTextNombreCliente: EditText? = null,
    private var editTextDomicilioCliente: EditText? = null,
    private var numberPickerTelefonoCliente: NumberPicker? = null,
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_cliente, container, false)
        editTextNombreCliente = view.findViewById(R.id.edit_text_nombre_cliente)
        editTextDomicilioCliente = view.findViewById(R.id.edit_text_domicilio_cliente)
        numberPickerTelefonoCliente = view.findViewById(R.id.number_picker_telefono_cliente)
        numberPickerTelefonoCliente?.minValue = 1//setMinValue(1)
        numberPickerTelefonoCliente?.maxValue = 10//setMaxValue(10)
//        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close)
//        title = "Add Cliente"

        return view
    }


    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_cliente_menu, menu)
        //        return super.onCreateOptionsMenu(menu);
        return true
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveNote() {
        val nombre = editTextNombreCliente!!.text.toString()
        val domicilio = editTextDomicilioCliente!!.text.toString()
        val telefono = numberPickerTelefonoCliente!!.value
        if (nombre.trim { it <= ' ' }.isEmpty() || domicilio.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(context, "Please insert a title and description", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val data = Intent()
        data.putExtra(EXTRA_NOMBRE_CLIENTE, nombre)
        data.putExtra(EXTRA_DOMICILIO_CLIENTE, domicilio)
        data.putExtra(EXTRA_TELEFONO_CLIENTE, telefono)
//        setResult(RESULT_OK, data)
//        finish()
    }

    companion object {
        const val EXTRA_NOMBRE_CLIENTE = "com.rodrigo.javier.eox.hackermen" +
                ".tutorialroomviewmodellivedatarecview.EXTRA_TITLE"
        const val EXTRA_DOMICILIO_CLIENTE = "com.rodrigo.javier.eox.hackermen" +
                ".tutorialroomviewmodellivedatarecview.EXTRA_DESCRIPTION"
        const val EXTRA_TELEFONO_CLIENTE = "com.rodrigo.javier.eox.hackermen" +
                ".tutorialroomviewmodellivedatarecview.EXTRA_PRIORITY"

        private const val ADD_NOTE_REQUEST = 1

        fun newInstance(): AddNoteFragment {
            var newaddNoteFragment_ = AddNoteFragment()
            return newaddNoteFragment_
        }

    }
}

