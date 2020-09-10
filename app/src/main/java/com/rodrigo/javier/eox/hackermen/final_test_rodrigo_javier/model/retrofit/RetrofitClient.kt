package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit


import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.utilities.CommonFunctions.Companion.baseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    companion object {
        fun getRetrofitObject(): ApiRetrofit {
            var retrofit =
                Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit.create(ApiRetrofit::class.java)
        }
    }

}