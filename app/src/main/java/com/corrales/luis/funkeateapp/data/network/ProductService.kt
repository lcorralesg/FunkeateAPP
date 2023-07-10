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
    //JSON: {"data":[{"id":128,"precio":85.0,"cantidad":1,"createdAt":null,"updateAt":null,"estado":false,"carrito":{"id":8,"cantidadTotal":1,"subtotal":85.0,"descuento":0.0,"total":100.3,"createdAt":"2023-07-09T04:54:10.000+00:00","updateAt":"2023-07-09T04:54:10.000+00:00","estado":true,"users":{"id":14,"username":"test","nickname":"test","email":"testmv@test.com","password":"$2b$10$n4fCJhIY.PGqLgtvCum4J.vZ/Apoy/o6UiAva/bRiLleCC1V7pKpS","emailVerified":false,"lastLogin":"2023-07-10T06:59:34.000+00:00","dateJoined":"2023-07-09T04:54:10.000+00:00","createdAt":"2023-07-09T04:54:10.000+00:00","active":true,"superuser":false,"staff":false}},"diseño":null,"producto":{"id":1,"nombre":"Iron Man","descripcion":"Funko de Iron Man personalizable","detalles":null,"precio":85.0,"imagen":"https://res.cloudinary.com/ddgwgczsf/image/upload/v1684698365/funkeate-products/1.png","createdAt":"2023-05-20T16:41:20.000+00:00","updateAt":"2023-05-22T18:14:19.000+00:00","estado":true,"cajaPersonalizada":true,"personalizable":true,"tamanoCaja":"16x12x9 cm","tamanoFunko":"13x6 cm","categoria":{"id":1,"nombre":"SuperHeroes","imagen":"https://res.cloudinary.com/ddgwgczsf/image/upload/v1684698365/funkeate-products/1.png","createdAt":"2023-05-22T16:36:42.000+00:00","updateAt":"2023-05-22T17:35:52.000+00:00","estado":true}}}],"count":1,"message":"Producto añadido correctamente"}
    @GET("carsdetail/findByCarrito_EstadoAndCarrito_Users_Id")
    suspend fun getCarritoDetailByUserId(@Query("id") userId: Int): Response<ProductListResponse>
}