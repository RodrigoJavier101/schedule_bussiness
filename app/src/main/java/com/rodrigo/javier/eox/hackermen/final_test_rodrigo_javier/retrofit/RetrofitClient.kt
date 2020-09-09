package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.retrofit

import retrofit2.Retrofit

class RetrofitClient {

    var retrofit =
        Retrofit.Builder().baseUrl("http://quotes.rest")
            .addConverterFactory(GsonConverterFactory.create()).build()
}