package com.corrales.luis.funkeateapp.data.model

import com.google.gson.annotations.SerializedName

//json que devuelve la api
//{"data":[{"id":2,"nombre":"Goku","descripcion":"Funko de Goku","precio":10.0,"imagen":"https://cdn.shopify.com/s/files/1/0057/8630/4600/products/f878221f-2c8d-425e-86c0-829ce87474eb_1200x1200.jpg?v=1680108606","created_at":"2023-05-21T23:56:48.000+00:00","update_at":"2023-05-21T23:56:48.000+00:00","estado":true,"categoria":{"id":1,"nombre":"Anime","created_at":"2023-05-21T23:53:18.000+00:00","update_at":"2023-05-21T23:53:18.000+00:00","estado":true}},{"id":3,"nombre":"Capitan America","descripcion":"Funko de Capitan America","precio":10.0,"imagen":"https://http2.mlstatic.com/D_NQ_NP_769355-MPE49800529984_042022-O.jpg","created_at":"2023-05-21T23:57:18.000+00:00","update_at":"2023-05-21T23:57:18.000+00:00","estado":true,"categoria":{"id":2,"nombre":"Marvel","created_at":"2023-05-21T23:53:18.000+00:00","update_at":"2023-05-21T23:53:18.000+00:00","estado":true}}],"count":2}

data class ProductListResponse (
    @SerializedName("data") val data: List<ProductResponse> = emptyList(),
    @SerializedName("count") val count: Int = 0
)