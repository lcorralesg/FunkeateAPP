package com.corrales.luis.funkeateapp.data.model

import com.google.gson.annotations.SerializedName

data class ProductListResponse (
<<<<<<< HEAD
    @SerializedName("count") val count: Int,
    @SerializedName("data") val data: List<ProductoResponse>,
=======
    @SerializedName("data") val data: List<ProductResponse>,
    @SerializedName("count") val count: Int
>>>>>>> 48dcc4a4d614da0f47de6d8012104a65696bb0a3
)