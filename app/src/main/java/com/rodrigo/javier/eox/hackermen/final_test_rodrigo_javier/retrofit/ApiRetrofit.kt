package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit

import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.api_objects.Json4Kotlin_Base
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.utilities.external.CommonFunctions.Companion.apiEndPoint
import retrofit2.Call
import retrofit2.http.GET

interface ApiRetrofit {
    @GET(apiEndPoint)
    fun getPhrase(): Call<Json4Kotlin_Base>
}