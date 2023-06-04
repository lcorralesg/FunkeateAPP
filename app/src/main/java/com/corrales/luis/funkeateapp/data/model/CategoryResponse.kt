package com.corrales.luis.funkeateapp.data.model

import com.google.gson.annotations.SerializedName

<<<<<<< HEAD
data class CategoryResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("update_at") val update_at: String,
    @SerializedName("estado") val estado: Boolean
)
=======
data class CategoryResponse(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("estado") val estado: Boolean,
    @SerializedName("imagen") val imagen: String,
    @SerializedName("id") val id: Int,
) {
    fun getCategoryId() = id
    fun getCategoryImage(): String = imagen
}
>>>>>>> feature_tinoco
