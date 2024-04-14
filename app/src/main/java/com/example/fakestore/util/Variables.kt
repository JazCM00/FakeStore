package com.example.fakestore.util

import android.app.Application

class Variables : Application() {
    companion object {
        var tokenGlobal: String = ""
        var logeado: Boolean = false
        var idCategoria: Int = 0
        var categoryName: String = ""
    }

    override fun onCreate() {
        super.onCreate()
        tokenGlobal = ""
        logeado = false
        idCategoria = 0
        categoryName = ""
    }
}