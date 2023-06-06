package com.corrales.luis.funkeateapp.data.network

import com.corrales.luis.funkeateapp.data.model.CategoryListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("categories/all")
    suspend fun getCategories() : Response<CategoryListResponse>
}