package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiRetrofit {
    @GET("qod.json")
    fun getPhrase(): Call<QuotePhrase>
}