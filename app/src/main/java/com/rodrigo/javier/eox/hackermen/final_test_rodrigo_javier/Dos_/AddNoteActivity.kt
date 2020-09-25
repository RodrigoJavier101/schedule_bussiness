package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R

class AddNoteActivity(
        private var editTextTitle: EditText? = null,
        private var editTextDescription: EditText? = null,
        private var numberPickerPriority: NumberPicker? = null,
) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        editTextTitle = findViewById(R.id.edit_text_title)
        editTextDescription = findViewById(R.id.edit_text_description)
        numberPickerPriority = findViewById(R.id.number_picker_priority)
        numberPickerPriority?.minValue = 1//setMinValue(1)
        numberPickerPriority?.maxValue = 10//setMaxValue(10)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close)
        title = "Add Note"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_note_menu, menu)
        //        return super.onCreateOptionsMenu(menu);
        return true
    }

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
        val title = editTextTitle!!.text.toString()
        val description = editTextDescription!!.text.toString()
        val priority = numberPickerPriority!!.value
        if (title.trim { it <= ' ' }.isEmpty() || description.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT)
                    .show()
            return
        }
        val data = Intent()
        data.putExtra(EXTRA_TITLE, title)
        data.putExtra(EXTRA_DESCRIPTION, description)
        data.putExtra(EXTRA_PRIORITY, priority)
        setResult(RESULT_OK, data)
        finish()
    }

    companion object {
        const val EXTRA_TITLE = "com.rodrigo.javier.eox.hackermen" +
                ".tutorialroomviewmodellivedatarecview.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.rodrigo.javier.eox.hackermen" +
                ".tutorialroomviewmodellivedatarecview.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "com.rodrigo.javier.eox.hackermen" +
                ".tutorialroomviewmodellivedatarecview.EXTRA_PRIORITY"
    }
}

