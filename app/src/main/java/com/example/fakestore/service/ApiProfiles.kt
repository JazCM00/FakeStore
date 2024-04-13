package com.example.fakestore.service

import com.example.fakestore.service.response.ProfileResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

interface ApiProfiles {
    //Ruta para consultar a la API el Login
    @GET
    suspend fun getProfile(
        @Url url: String,
        @Header("Authorization") token: String
    ): ProfileResponse
}

object ProfileFactory {
    fun getProfileRetrofit(): ApiProfiles {
            return Retrofit.Builder()
                .baseUrl("https://api.escuelajs.co/api/v1/auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiProfiles::class.java)
    }
}

