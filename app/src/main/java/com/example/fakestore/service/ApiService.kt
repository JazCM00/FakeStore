package com.example.fakestore.service

import com.example.fakestore.service.response.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    //Ruta para consultar a la API el Login
    @POST( value = "login")
    fun postLogin(@Query(value = "email") email: String, @Query (value = "password") password: String ):
            Call<LoginResponse>

    //Consulta a la API
    companion object Factory{
        private const val BASE_URL = "https://api.escuelajs.co/api/v1/auth/"
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

}