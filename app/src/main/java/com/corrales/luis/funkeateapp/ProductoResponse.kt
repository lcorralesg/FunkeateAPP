package com.corrales.luis.funkeateapp

import com.google.gson.annotations.SerializedName

data class ProductoResponse(
    @SerializedName("data") var status: Int,
    @SerializedName("message") var message: String,
    @SerializedName("posts") var posts: List<Producto>

)
