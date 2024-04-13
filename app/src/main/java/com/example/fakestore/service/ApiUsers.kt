package com.example.fakestore.service

import com.example.fakestore.model.modelUserResponse.UserResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiUsers {
    @GET
    suspend fun getUsers(@Url url:String) : UserResponse
}

object RetrofitServiceFactory{
    //Funcion q lanza la peticion de retrofit
    fun getUserRetrofit():ApiUsers {
        return Retrofit.Builder()
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiUsers::class.java)
    }
}