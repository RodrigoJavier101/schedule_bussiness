package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ruta


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.ApiRetrofit
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.api_objects.Json4Kotlin_Base
import retrofit2.Call

class RutaFragment : Fragment() {

    private lateinit var service: ApiRetrofit
    private lateinit var call: Call<Json4Kotlin_Base>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_ruta, container, false)

        return view
    }


    companion object {
        fun newInstance(): RutaFragment {
            var newRutaFragment = RutaFragment()
            return newRutaFragment
        }
    }

}