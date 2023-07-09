package com.corrales.luis.funkeateapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.corrales.luis.funkeateapp.data.model.CategoryResponse
import com.corrales.luis.funkeateapp.data.model.ProductResponse
import com.corrales.luis.funkeateapp.data.network.ApiService
import com.corrales.luis.funkeateapp.data.network.ProductService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatalogoViewModel : ViewModel() {
    val productList = MutableLiveData<List<ProductResponse>>()
    val catalogList = MutableLiveData<List<CategoryResponse>>()
    val productDetail = MutableLiveData<ProductResponse>()
    val isLoading = MutableLiveData<Boolean>()

    fun getAllProducts(context: Context) {
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(false)
            val call = getRetrofit().create(ProductService::class.java).getAllProducts()
            if (call.isSuccessful) {
                call.body()?.let {
                    productList.postValue(it.data)
                }
            }
        }
    }

    fun getAllCategories(context: Context) {
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(false)
            val call = getRetrofit().create(ApiService::class.java).getCategories()
            if (call.isSuccessful) {
                call.body()?.let {
                    catalogList.postValue(it.data)
                }
            }
        }
    }

    fun getProductsByCategoryName(categoryName: String) {
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(false)
            val call = getRetrofit().create(ProductService::class.java).getProductsByCategoryName(categoryName)
            if (call.isSuccessful) {
                call.body()?.let {
                    productList.postValue(it.data)
                }
            }
        }
    }

    fun getProductDetailById(productId: Int) {
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(false)
            val call = getRetrofit().create(ProductService::class.java).getProductDetailById(productId)
            if (call.isSuccessful) {
                call.body()?.let {
                    productDetail.postValue(it)
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://ec2-18-188-19-110.us-east-2.compute.amazonaws.com:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}