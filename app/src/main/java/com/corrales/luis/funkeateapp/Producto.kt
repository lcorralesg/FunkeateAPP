package com.corrales.luis.funkeateapp

import com.google.gson.annotations.SerializedName

data class Producto(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("precio") val precio: Double,
    @SerializedName("tamaño_funko") val tamañofunko: String,
    @SerializedName("tamaño_caja") val tamañocaja: String,
    @SerializedName("personalizable") val personalizable: Boolean,
    @SerializedName("caja_personalizada") val caja_personalizada: Boolean,
    @SerializedName("imagen") val imagen: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("estado") val estado: Boolean,
    @SerializedName("categoria_id") val categoria : Int
    )
