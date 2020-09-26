package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.GestionDao
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.RoomApplication
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.User_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.utilities.external.CommonFunctions.Companion.fileNameShPref
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity(){  //, SharedPreferences.OnSharedPreferenceChangeListener {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var spinner_login: Spinner
    lateinit var edit_password_login: EditText
    lateinit var dao: GestionDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        dao = RoomApplication.gestionDatabase.getGestionDao()
        initViews()
        startSpinner()
        sharedPreferences = getSharedPreferences(fileNameShPref, Context.MODE_PRIVATE)
        btn_login.setOnClickListener {
//            saveInSharedPreferences(it)
            fetchMainActivity(applicationContext)
//            validateUser()
        }
    }

    private fun validateUser() {
        var passResp: Int = 0
        var username = spinner_login.selectedItem.toString()
        var listaUsers: List<User_Entity> = listOf()
        var editloginpass = findViewById<EditText>(R.id.text_edit_password)
        CoroutineScope(Dispatchers.IO).launch {
//            passResp = dao.getPasswordFromUserTable(username)
            if (passResp.toString().equals(editloginpass.text.toString())) {
//                fetchMainActivity(applicationContext)
                finish()
            }
        }

//        Toast.makeText(this, "La pass no coincide ${passResp}", Toast.LENGTH_SHORT).show()
    }

    private fun fillSpinner() {
        var users_list: List<User_Entity>? = null//dao.getAllFromUserTable()
        var usersName: ArrayList<String> = arrayListOf()
        users_list?.forEach {
            usersName.add(it.user_name)
        }

        spinner_login.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, usersName)
    }

    fun initViews() {
        spinner_login = findViewById<Spinner>(R.id.spinner_user_login)
        edit_password_login = findViewById<EditText>(R.id.text_edit_password)
    }

    private fun startSpinner() {
        val userAdmin = User_Entity("", 9999)
        CoroutineScope(Dispatchers.IO).launch {

            /*   if (dao.getAllFromUserTable().isEmpty() || dao.getAllFromUserTable() === null) {
                   dao.insertUsers(userAdmin)
                   fillSpinner()
               } else {
                   fillSpinner()
               }*/
        }

    }

    private fun fetchMainActivity(context: Context) {
        val intent = Intent(this, MainActivity::class.java)
        return startActivity(intent)
    }

    /*override fun onSharedPreferenceChanged(
        changedSharedPreferences: SharedPreferences?,
        changedKey: String?
    ) {
        if (changedKey.equals("fabiola", true)) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        }

        if (changedKey.equals("f", true)) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        }
    }*/

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