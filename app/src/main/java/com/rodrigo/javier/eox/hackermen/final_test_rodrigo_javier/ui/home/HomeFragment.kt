package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.retrofit.ApiRetrofit
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.retrofit.RetrofitClient
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.retrofit.objects.Json4Kotlin_Base
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var service: ApiRetrofit
    private lateinit var call: Call<Json4Kotlin_Base>


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

        service =
            RetrofitClient.getRetrofitObject()
        call = service.getPhrase()

        call.enqueue(object : Callback<Json4Kotlin_Base> {

            override fun onResponse(
                call: Call<Json4Kotlin_Base>,
                response: Response<Json4Kotlin_Base>
            ) {
                val texto: String

                try {
                    texto = response.code().toString()
                    text_home.text = texto + "  -- " + response.body()?.dolar.toString()
                } catch (t: Throwable) {
                    text_home.text = t.message.toString() + " ----"
                }
            }

            override fun onFailure(call: Call<Json4Kotlin_Base>, t: Throwable) {
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

    override fun onDestroy() {
        super.onDestroy()
        call.cancel()
    }
}