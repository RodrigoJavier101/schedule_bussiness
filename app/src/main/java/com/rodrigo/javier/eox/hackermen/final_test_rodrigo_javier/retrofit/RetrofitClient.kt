package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.retrofit

import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.utilities.CommonFunctions.Companion.baseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    companion object {
        fun getRetrofitObject(): Retrofit {
            var retrofit =
                Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit
        }
    }

}