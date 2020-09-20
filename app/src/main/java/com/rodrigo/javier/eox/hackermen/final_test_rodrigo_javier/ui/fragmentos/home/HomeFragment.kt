package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.ApiRetrofit
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.RetrofitClient
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.api_objects.Json4Kotlin_Base
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionDao
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.RoomApplication
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.utilities.external.CommonFunctions
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var service: ApiRetrofit
    private var call: Call<Json4Kotlin_Base>? = null
    private val dao: GestionDao = RoomApplication.gestionDatabase.getGestionDao()
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var btnAdmin: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        sharedPreferences = requireActivity().getSharedPreferences(
            CommonFunctions.fileNameShPref,
            Context.MODE_PRIVATE
        )
        btnAdmin = root.findViewById(R.id.btn_admin_)
        Toast.makeText(context, container?.id.toString(), Toast.LENGTH_LONG).show()
        callIndicators()
        readFromSHPref()
        setUpAddButton(root, container?.id.toString())
        return root
    }

    private fun readFromSHPref() {
        /* Toast.makeText(
             context,
             sharedPreferences.getString("NombreUsuario", "") + " - " +
                     sharedPreferences.getString("PasswordUsuario", ""),
             Toast.LENGTH_LONG
         ).show()*/
        if (sharedPreferences.getString("NombreUsuario", "").equals("Admin")) {
            btnAdmin.visibility = View.VISIBLE
        } else {
            btnAdmin.visibility = View.GONE
        }
    }

    private fun callIndicators() {

        service =
            RetrofitClient.getRetrofitObject()
        call = service.getPhrase()

        call!!.enqueue(object : Callback<Json4Kotlin_Base> {

            override fun onResponse(
                call: Call<Json4Kotlin_Base>,
                response: Response<Json4Kotlin_Base>
            ) {
                val json: Json4Kotlin_Base

                try {
                    json = response.body()!!
                    lbl_dolar.text = json.dolar.valor.toString()
                    lbl_euro.text = json.euro.valor.toString()
                    lbl_uf.text = json.uf.valor.toString()
                    lbl_utm.text = json.utm.valor.toString()
                    lbl_desempleo.text = json.tasa_desempleo.valor.toString() + "%"
                } catch (t: Throwable) {
                    Log.d("ERROR capa 8 --->", t.message.toString())
                }
            }

            override fun onFailure(call: Call<Json4Kotlin_Base>, t: Throwable) {

            }
        })
    }

    companion object {
        fun newInstance(): HomeFragment {
            var newHomeFragment = HomeFragment()
            return newHomeFragment
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        call!!.cancel()
    }

    private fun setUpAddButton(view: View, containerID: String?) {
        btnAdmin.setOnClickListener {
            fetchUsersFragment(containerID)
        }
    }

    private fun fetchUsersFragment(container: String?) {

        Toast.makeText(context, container, Toast.LENGTH_SHORT).show()
    }
}
