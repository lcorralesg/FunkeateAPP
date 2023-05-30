package com.corrales.luis.funkeateapp

import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("products/all")
    fun fecthAllProducts(@Header("Authorization") token: String): Call<List<Producto>>
}