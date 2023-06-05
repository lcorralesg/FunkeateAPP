package com.corrales.luis.funkeateapp.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ProductResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("caja_personalizada") val caja_personalizada: Boolean,
    @SerializedName("created_at") val created_at: Date,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("detalles") val detalles: String,
    @SerializedName("estado") val estado: Boolean,
    @SerializedName("imagen") val imagen: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("personalizable") val personalizable: Boolean,
    @SerializedName("precio") val precio: Double,
    @SerializedName("tamaño_caja") val tamaño_caja: String,
    @SerializedName("tamaño_funko") val tamaño_funko: String,
    @SerializedName("update_at") val update_at: String,
    @SerializedName("categoria_id") val categoria: CategoryResponse,
) {

    fun getProductId() = id
    fun getCategory() = categoria
    fun getProductImage(): String = imagen
}
