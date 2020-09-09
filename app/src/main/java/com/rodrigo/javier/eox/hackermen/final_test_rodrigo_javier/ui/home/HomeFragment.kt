package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.home

import Json4Kotlin_Base
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.retrofit.ApiRetrofit
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.retrofit.RetrofitClient
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.admin_.AdminFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.nav_header_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        callToPhrase()

        return root
    }

    private fun callToPhrase() {
        var service: ApiRetrofit =
            RetrofitClient.getRetrofitObject().create(ApiRetrofit::class.java)
        val call: Call<List<Json4Kotlin_Base>> = service.getPhrase()

        call.enqueue(object : Callback<List<Json4Kotlin_Base>> {

            override fun onResponse(
                call: Call<List<Json4Kotlin_Base>>,
                response: Response<List<Json4Kotlin_Base>>
            ) {
                val texto: String

                try {
                    texto = response.body()!!.get(0).biography?.fullName
                    text_home.text = texto
                } catch (e: Exception) {
                    text_home.text = e.message.toString()
                }


            }

            override fun onFailure(call: Call<List<Json4Kotlin_Base>>, t: Throwable) {
                text_home.text = t.message.toString()
            }

        })
    }


    companion object {
        fun newInstance(): HomeFragment {
            var newHomeFragment = HomeFragment()
            return newHomeFragment
        }
    }
}