package com.corrales.luis.funkeateapp.data.model

//http://ec2-18-188-19-110.us-east-2.compute.amazonaws.com:8080/carsdetail/findByCarrito_EstadoAndCarrito_Users_Id/14
data class CarritoResponse(
    val data: List<CarritoData>,
    val count: Int,
    val message: String
)

data class CarritoData(
    val id: Int,
    val precio: Double,
    val cantidad: Int,
    val createdAt: String?,
    val updateAt: String?,
    val estado: Boolean,
    val diseño: Any?,
    val producto: Producto
)

data class Producto(
    val nombre: String,
    val precio: Double,
    val imagen: String,
)