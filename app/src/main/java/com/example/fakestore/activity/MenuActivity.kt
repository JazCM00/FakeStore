package com.example.fakestore.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.fakestore.R
import com.example.fakestore.model.modelProfileResponse.ProfileResponseItem
import com.example.fakestore.model.modelUserResponse.UserResponseItem
import com.example.fakestore.service.ApiProfiles
import com.example.fakestore.service.ProfileFactory
import com.example.fakestore.service.RetrofitServiceFactory
import com.example.fakestore.service.response.ProfileResponse
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MenuActivity : AppCompatActivity() {
    //Variable para depurar
    private val TAG = "MenuActivity"

    //Variables de los componentes
    private lateinit var btnUsuarios: Button
    private lateinit var btnCategorias: Button
    private lateinit var btnSalir: Button
    private lateinit var imgProfile: ImageView
    private lateinit var txtName: TextView
    private lateinit var txtRole: TextView

    // Almacenar
    private val profilesInit = mutableListOf<ProfileResponseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        Log.i(TAG,"**************************************************Entro Menu")
        //Inicializacion
        InitializationComponents()
        InitializationListener()
        InitializationProfile()
    }

    //Funcion q Inicializa los datos
    private fun InitializationProfile(){
        //Recogemos el valor
        val paquete: Bundle? = intent.extras
        var accessToken: String = paquete?.getString("access_token") ?: ""
        Log.i(TAG,"**************************************************El valor Menu es $accessToken")

        //Crear la peticion
        val apiProfileService = ProfileFactory.getProfileRetrofit()
        lifecycleScope.launch {
            //hacemos la peticion
            val data = apiProfileService.getProfile(
                "profile",
                "Bearer "+ accessToken
            )
            Log.i(TAG, "**************************************************Los datos son $data")
            txtName.text = data.name
            txtRole.text = data.role
            Picasso.get()
                .load(data.avatar)
                .error(R.mipmap.ic_launcher_round)
                .into(imgProfile);
        }
    }

    //Funcion q Inicializa todos los componentes
    private fun InitializationComponents(){
        btnUsuarios = findViewById<Button>(R.id.btnUsuarios)
        btnCategorias = findViewById<Button>(R.id.btnCategorias)
        btnSalir = findViewById<Button>(R.id.btnSalir)
        imgProfile = findViewById<ImageView>(R.id.imgProfile)
        txtName = findViewById<TextView>(R.id.txtName)
        txtRole = findViewById<TextView>(R.id.txtRole)
    }

    //Funcion que inicializa los Listener
    private fun InitializationListener(){

        //Ir a suarios
        btnUsuarios.setOnClickListener{
            Log.i(TAG,"**************************************************Se preciono el boton")
            goToUser()
        }
        btnCategorias.setOnClickListener{
            Log.i(TAG,"**************************************************Se preciono el boton")
            gotoCategory()
        }
        //Salir de la aplicacion
        btnSalir.setOnClickListener{
            goToSalir()
        }
    }

    //Funcion para salir de la aplicacion
    private fun goToUser(){
        Log.i(TAG,"**************************************************Entro en User")
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun gotoCategory(){
        Log.i(TAG,"**************************************************Entro en Categorie")
        val intent = Intent(this, CategoryActivity::class.java)
        startActivity(intent)
        finish()
    }

    //Funcion para salir de la aplicacion
    private fun goToSalir(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}