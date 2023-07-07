package com.corrales.luis.funkeateapp.data.network

import com.corrales.luis.funkeateapp.data.model.ProductListResponse
import com.corrales.luis.funkeateapp.data.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("products/all")
    suspend fun getAllProducts(): Response<ProductListResponse>

    //http://localhost:8080/products/findbycategoryname?cname=anime
    @GET("products/findbycategoryname")
    suspend fun getProductsByCategoryName(@Query("cname") categoryName: String): Response<ProductListResponse>
}