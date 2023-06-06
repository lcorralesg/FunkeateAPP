package com.corrales.luis.funkeateapp.data.model

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("estado") val estado: Boolean,
    @SerializedName("imagen") val imagen: String,
) {
    fun getCategoryId() = id
    fun getCategoryImage(): String = imagen
}
