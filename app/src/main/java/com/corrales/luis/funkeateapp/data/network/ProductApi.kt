package com.corrales.luis.funkeateapp.data.network

import com.corrales.luis.funkeateapp.data.model.ProductListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts():
        Response<ProductListResponse>
}