package com.corrales.luis.funkeateapp.data.model

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("estado") val estado: Boolean,
    @SerializedName("imagen") val imagen: String,
    @SerializedName("id") val id: Int,
) {
    fun getCategoryId() = id
    fun getCategoryImage(): String = imagen
}

