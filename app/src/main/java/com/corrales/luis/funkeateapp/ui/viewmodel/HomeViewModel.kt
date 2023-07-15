package com.corrales.luis.funkeateapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corrales.luis.funkeateapp.data.model.CategoryResponse
import com.corrales.luis.funkeateapp.data.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel : ViewModel() {
    val categoryList = MutableLiveData<List<CategoryResponse>>()
    val isLoading = MutableLiveData<Boolean>()


    init{
        getAllCategories()
    }

    private fun getAllCategories(){
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(false)
            val call = getRetrofit().create(ApiService::class.java).getCategories()
            if(call.isSuccessful){
                call.body()?.let {
                    categoryList.postValue(it.data)
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://ec2-3-137-205-145.us-east-2.compute.amazonaws.com:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}