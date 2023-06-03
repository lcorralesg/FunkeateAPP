package com.corrales.luis.funkeateapp.data.model

import com.google.gson.annotations.SerializedName

data class CategoryListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("data") val data: List<CategoryResponse>,
)
