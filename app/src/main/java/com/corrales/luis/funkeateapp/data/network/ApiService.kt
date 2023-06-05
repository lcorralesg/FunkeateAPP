package com.corrales.luis.funkeateapp.data.network

import com.corrales.luis.funkeateapp.data.model.CategoryListResponse
import com.corrales.luis.funkeateapp.data.model.ProductListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    /*@GET("products/all")
    fun fecthAllProducts(@Header("Authorization") token: String): Call<List<Producto>>*/

    @GET("categories/all")
    suspend fun getCategories() : Response<CategoryListResponse>

    @GET("products/all")
    suspend fun getProducts(): Response<ProductListResponse>

    @GET("products/findbycategory/{categoria_name}")
    suspend fun getProductsbyCategoryName(@Path("categoria_name") categoria_name:String):Response<ProductListResponse>
}