package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.GestionDao
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.GestionDatabase
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.User_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.utilities.external.CommonFunctions.Companion.fileNameShPref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity :
    AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var passwordText: TextView
    private lateinit var usuarioText: TextView
    private lateinit var loginText: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var spinnerLogin: Spinner
    private lateinit var editPasswordLogin: EditText
    private lateinit var btnLogin: Button

    //    val repository: GestionRepository
    lateinit var dao: GestionDao
//    val allUsers: LiveData<List<User_Entity>?>?

    init {
//        repository = GestionRepository(this.application!!)
//        allUsers = repository.allUsers_
//        dao = GestionDatabase.getInstance(applicationContext)!!.setDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        dao = GestionDatabase.getInstance(applicationContext)!!.setDao()
        initViews()
        startSpinner()
        sharedPreferences = getSharedPreferences(fileNameShPref, Context.MODE_PRIVATE)
        btnLogin.setOnClickListener {
            saveInSharedPreferences(it)
            validateUser()
        }
    }

    private fun validateUser() {
        var passResp: Int? = 0
        var username = spinnerLogin.selectedItem.toString()
        var listaUsers: List<User_Entity> = listOf()
        var editloginpass = findViewById<EditText>(R.id.text_edit_password)

        CoroutineScope(Dispatchers.IO).launch {
            passResp = dao.getPasswordFromUserTable(username)
            if (passResp.toString().equals(editloginpass.text.toString()) &&
                !username.toString().equals("Selecciona un usuario")
            ) {
                fetchMainActivity(applicationContext)
            } else {
//              el toast se debe llamar dentro del main thread
                callToast()
            }

        }

    }

    private fun callToast() {
//        @MainThread
//        Toast.makeText(this@LoginActivity, "el password no corresponde", Toast.LENGTH_SHORT)
//            .show()

    }

    private fun fillSpinner() {
        var users_list: List<User_Entity> = dao.getAllUsers_2()
        var usersName: ArrayList<String> = arrayListOf()
        users_list?.forEach {
            usersName.add(it!!.user_name)
        }

        spinnerLogin.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, usersName)
    }

    private fun initViews() {
        loginText = findViewById<TextView>(R.id.text_view_login)
        usuarioText = findViewById<TextView>(R.id.text_view_usuario)
        spinnerLogin = findViewById<Spinner>(R.id.spinner_user_login)
        passwordText = findViewById<TextView>(R.id.text_view_password)
        editPasswordLogin = findViewById<EditText>(R.id.text_edit_password)
        btnLogin = findViewById(R.id.btn_login)
    }

    private fun startSpinner() {
        val firstEntry = User_Entity("Selecciona un usuario")
        val userAdmin = User_Entity("Admin", 9999)
        CoroutineScope(Dispatchers.IO).launch {
            if (dao.getAllUsers_2() === null || dao.getAllUsers_2().isEmpty()) {
                dao.insertUser(firstEntry)
                dao.insertUser(userAdmin)
                fillSpinner()
            } else {
                fillSpinner()
            }
        }

    }

    private fun fetchMainActivity(context: Context) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
//Creo que debe ser en el main thread
        brokeLayout()
        finish()
    }

    private fun brokeLayout() {
 /*       YoYo.with(Techniques.Hinge)
            .duration(100) //.repeat(1)
            .playOn(loginText)

        YoYo.with(Techniques.Hinge)
            .duration(100) //.repeat(1)
            .playOn(
                usuarioText
            )

        YoYo.with(Techniques.Hinge)
            .duration(100) //.repeat(1)
            .playOn(spinnerLogin)

        YoYo.with(Techniques.Hinge)
            .duration(100) //.repeat(1)
            .playOn(passwordText)

        YoYo.with(Techniques.Hinge)
            .duration(100) //.repeat(1)
            .playOn(editPasswordLogin)

        YoYo.with(Techniques.Hinge)
            .duration(100) //.repeat(1)
            .playOn(btnLogin)*/
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
                .putString("NombreUsuario", spinnerLogin.selectedItem.toString())
                .apply()
            sharedPreferences.edit()
                .putString("PasswordUsuario", editPasswordLogin.text.toString())
                .apply()

            /*Borrar*/
//            sharedPreferences.edit().remove(claveTexto).apply()
        }

    }

}