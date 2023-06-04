package com.corrales.luis.funkeateapp.data.model

import com.google.gson.annotations.SerializedName

data class CategoryResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("update_at") val update_at: String,
    @SerializedName("estado") val estado: Boolean
)