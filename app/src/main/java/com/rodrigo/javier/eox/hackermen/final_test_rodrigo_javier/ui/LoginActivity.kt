package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            fetchMainActivity(applicationContext)
            finish()
        }

    }


    fun fetchMainActivity(context: Context) {
        val intent = Intent(this, MainActivity::class.java)
        return startActivity(intent)
    }
}