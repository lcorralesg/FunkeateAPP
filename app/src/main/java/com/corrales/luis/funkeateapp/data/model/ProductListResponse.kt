package com.corrales.luis.funkeateapp.data.model

import com.google.gson.annotations.SerializedName

data class ProductListResponse (
    @SerializedName("count") val count: Int,
    @SerializedName("data") val data: List<ProductoResponse>,
)