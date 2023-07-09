package com.corrales.luis.funkeateapp.data.network

import com.corrales.luis.funkeateapp.data.model.ProductListResponse
import com.corrales.luis.funkeateapp.data.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("products/all")
    suspend fun getAllProducts(): Response<ProductListResponse>

    //http://ec2-18-188-19-110.us-east-2.compute.amazonaws.com:8080/products/findbycategoryname?cname=anime
    @GET("products/findbycategoryname")
    suspend fun getProductsByCategoryName(@Query("cname") categoryName: String): Response<ProductListResponse>

    //http://ec2-18-188-19-110.us-east-2.compute.amazonaws.com:8080/products/find?producto_id=1
    @GET("products/find")
    suspend fun getProductDetailById(@Query("producto_id") productId: Int): Response<ProductResponse>

    //http://ec2-18-188-19-110.us-east-2.compute.amazonaws.com:8080/carsdetail/findByCarrito_EstadoAndCarrito_Users_Id/14
    @GET("carsdetail/findByCarrito_EstadoAndCarrito_Users_Id")
    suspend fun getCarritoDetailByUserId(@Query("id") userId: Int): Response<ProductListResponse>
}