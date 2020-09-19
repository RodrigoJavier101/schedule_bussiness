package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.utilities.external.CommonFunctions.Companion.fileNameShPref
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*

class LoginActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var spinner_login: Spinner
    lateinit var edit_password_login: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences(fileNameShPref, Context.MODE_PRIVATE)
        btn_login.setOnClickListener {
            saveInSharedPreferences(it)
            fetchMainActivity(applicationContext)
            finish()
        }
    }

    fun initViews() {
        spinner_login = findViewById<Spinner>(R.id.spinner_user_login)
        edit_password_login = findViewById<EditText>(R.id.text_edit_password)
    }

    fun fetchMainActivity(context: Context) {
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
        initViews()
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