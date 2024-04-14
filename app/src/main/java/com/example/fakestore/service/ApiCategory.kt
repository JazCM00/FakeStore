package com.example.fakestore.service

import com.example.fakestore.model.modelCategoryResponse.CategoryResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiCategory {
    @GET
    suspend fun getCategory(@Url url:String) : CategoryResponse
}

object CategoryFactory{
    //Funcion q lanza la peticion de retrofit
    fun getCategoryRetrofit():ApiCategory {
        return Retrofit.Builder()
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiCategory::class.java)
    }
}