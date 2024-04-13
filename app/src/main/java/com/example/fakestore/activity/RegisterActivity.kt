package com.example.fakestore.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fakestore.R


class RegisterActivity : AppCompatActivity() {
    //Variables de los componentes
    private lateinit var txtName: TextView
    private lateinit var btnLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Inicializacion
        InitializationComponents()
        InitializationListener()

    }

    //Funcion q Inicializa todos los componentes
    private fun InitializationComponents(){
        btnLogin = findViewById<TextView>(R.id.txtLogin)
    }

    //Funcion que inicializa los Listener
    private fun InitializationListener(){

        //Cambio a la pantalla de Registro
        btnLogin.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}