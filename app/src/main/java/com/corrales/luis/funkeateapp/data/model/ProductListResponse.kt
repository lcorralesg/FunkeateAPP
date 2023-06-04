package com.corrales.luis.funkeateapp.data.model

import com.google.gson.annotations.SerializedName

data class ProductListResponse (

    @SerializedName("data") val data: List<ProductResponse>,
    @SerializedName("count") val count: Int
)