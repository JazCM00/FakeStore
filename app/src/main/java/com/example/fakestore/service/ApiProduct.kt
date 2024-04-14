package com.example.fakestore.service

import com.example.fakestore.model.modelProductResponse.ProductResponse
import com.example.fakestore.util.Variables
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiProduct {
    @GET
    suspend fun getProduct(@Url url: String): ProductResponse
}

object ProductFactory {
    //Funcion q lanza la peticion
    fun getProductRetrofit(): ApiProduct {
        return Retrofit.Builder()
            .baseUrl("https://api.escuelajs.co/api/v1/products/?categoryId=" + Variables.idCategoria.toString())
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiProduct::class.java)
    }
}