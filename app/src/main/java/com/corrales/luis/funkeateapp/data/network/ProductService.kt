package com.corrales.luis.funkeateapp.data.network

import com.corrales.luis.funkeateapp.data.model.ProductListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("products/all")
    suspend fun getAllProducts(): Response<ProductListResponse>
}