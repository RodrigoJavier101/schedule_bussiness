package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.Toast.LENGTH_LONG
import com.google.android.material.textfield.TextInputEditText
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionDao
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.RoomApplication
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.User_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters.Lista_Inventario_Adapter
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.utilities.external.CommonFunctions.Companion.fileNameShPref
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var spinner_login: Spinner
    lateinit var edit_password_login: EditText
    lateinit var dao: GestionDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        dao = RoomApplication.gestionDatabase.getGestionDao()
        initViews()
        startSpinner()
        sharedPreferences = getSharedPreferences(fileNameShPref, Context.MODE_PRIVATE)
        btn_login.setOnClickListener {
            saveInSharedPreferences(it)
            fetchMainActivity(applicationContext)
            finish()
        }
    }

    private fun fillSpinner() {
        val users_list: List<User_Entity> = dao.getAllFromUserTable()
        var usersName: List<String> = listOf()
        users_list.forEach {
            usersName = listOf(it.user_name)
        }
        spinner_login.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, usersName)

        /*spinner_login.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@LoginActivity, myStrings[p2], LENGTH_LONG).show()
            }

        }*/
    }

    fun initViews() {
        spinner_login = findViewById<Spinner>(R.id.spinner_user_login)
        edit_password_login = findViewById<EditText>(R.id.text_edit_password)
    }

    private fun startSpinner() {
        val userAdmin = User_Entity(0, "Admin", 9999)
        CoroutineScope(Dispatchers.IO).launch {
            if (dao.getAllFromUserTable().isEmpty()) {
                dao.insertUsers(userAdmin)
                fillSpinner()
            } else {
                fillSpinner()
            }
        }

    }

    private fun fetchMainActivity(context: Context) {
        val intent = Intent(this, MainActivity::class.java)
        return startActivity(intent)
    }

    override fun onSharedPreferenceChanged(
        changedSharedPreferences: SharedPreferences?,
        changedKey: String?
    ) {
        if (changedKey.equals("fabiola", true)) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        }

        if (changedKey.equals("f", true)) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveInSharedPreferences(it: View) {
        if (this::sharedPreferences.isInitialized) {

            sharedPreferences.edit()
                .putString("NombreUsuario", spinner_login.selectedItem.toString())
                .apply()
            sharedPreferences.edit()
                .putString("PasswordUsuario", edit_password_login.text.toString())
                .apply()

            /*Borrar*/
//            sharedPreferences.edit().remove(claveTexto).apply()
        }

    }
}