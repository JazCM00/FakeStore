package com.example.fakestore.util

import android.app.Application

class Variables : Application() {
    companion object {
        var tokenGlobal: String = ""
        var logeado: Boolean = false
    }

    override fun onCreate() {
        super.onCreate()
        tokenGlobal = ""
        logeado = false
    }
}