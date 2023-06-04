package com.corrales.luis.funkeateapp.data.network

import com.corrales.luis.funkeateapp.Producto
import com.corrales.luis.funkeateapp.data.model.CategoryListResponse
import com.corrales.luis.funkeateapp.data.model.CategoryResponse
import com.corrales.luis.funkeateapp.data.model.ProductListResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    /*@GET("products/all")
    fun fecthAllProducts(@Header("Authorization") token: String): Call<List<Producto>>*/

    @GET("categories/all")
    suspend fun getCategories() : Response<CategoryListResponse>

    @GET("products/all")
    suspend fun getProducts(): Response<ProductListResponse>
}