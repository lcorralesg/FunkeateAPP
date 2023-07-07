package com.corrales.luis.funkeateapp.data.model

import com.google.gson.annotations.SerializedName

data class ProductResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("detalles") val detalles: String,
    @SerializedName("precio") val precio: Double,
    @SerializedName("imagen") val imagen: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("update_at") val update_at: String,
    @SerializedName("estado") val estado: Boolean,
    @SerializedName("caja_personalizada") val caja_personalizada: Boolean,
    @SerializedName("personalizable") val personalizable: Boolean,
    @SerializedName("tamanoCaja") val tamanoCaja: String,
    @SerializedName("tamanoFunko") val tamanoFunko: String,
    @SerializedName("categoria") val categoria: CategoryResponse
)
