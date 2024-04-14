package com.example.fakestore.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.fakestore.R
import com.example.fakestore.model.modelProfileResponse.ProfileResponseItem
import com.example.fakestore.service.ProfileFactory
import com.example.fakestore.util.LoadingDialog
import com.example.fakestore.util.PreferenceHelper
import com.example.fakestore.util.PreferenceHelper.PreferenceHelper.get
import com.example.fakestore.util.PreferenceHelper.PreferenceHelper.set
import com.example.fakestore.util.Variables
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

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

    private lateinit var accessToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        Log.i(TAG,"**************************************************Entro en Menu")
        Log.i(TAG,"**************************************************${Variables.logeado}")
        // Abrimos la vista Bienvenidos
        if (Variables.logeado == false) {
            val loading = LoadingDialog(this)
            loading.startLoading()
            val handler = Handler()
            handler.postDelayed(
                object : Runnable {
                    override fun run() {
                        loading.isDismiss()
                    }
                }, 5000
            )
            Variables.logeado = true
        }
        //Inicializacion
        InitializationComponents()
        InitializationListener()
        InitializationProfile()
    }

    //Funcion q Inicializa los datos
    private fun InitializationProfile(){


        //Verificamos si la sesion esta creada
        val preferences = PreferenceHelper.PreferenceHelper.defaultPrefs(this)
        if (preferences["session"]){
            Log.i(TAG,"**************************************************Sesion creada")
            //Cargamos el token guardado
            accessToken = Variables.tokenGlobal
        }else {
            Log.i(TAG,"**************************************************Sesion no creada")
            Variables.logeado = true
            //Recogemos el valor token enviado desde Main
            val paquete: Bundle? = intent.extras
            accessToken = paquete?.getString("access_token") ?: ""
        }
        Log.i(TAG,"**************************************************Token es $accessToken")

        peticionApi()

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
            goToUser()
        }
        btnCategorias.setOnClickListener{
            gotoCategory()
        }
        //Salir de la aplicacion
        btnSalir.setOnClickListener{
            goToSalir()
        }
    }

    //Funcion para ir a la pantalla Usuarios
    private fun goToUser(){
        Log.i(TAG,"**************************************************Salio de Menu a User")
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent)
        finish()
    }

    //Funcion para ir a la pantalla Categorias
    private fun gotoCategory(){
        Log.i(TAG,"**************************************************Salio de Menu en Categoria")
        val intent = Intent(this, CategoryActivity::class.java)
        startActivity(intent)
        finish()
    }

    //Funcion para salir de la aplicacion
    private fun goToSalir(){
        Log.i(TAG,"**************************************************Salio Aplicacion")
        val intent = Intent(this, MainActivity::class.java)
        closeSession()
        Variables.tokenGlobal = ""
        Variables.logeado = false
        startActivity(intent)
        finish()
    }

    //Funcion para crear la session
    private fun closeSession(){
        val preferences = PreferenceHelper.PreferenceHelper.defaultPrefs(this)
        preferences["session"] = false
    }

    //Funcion para la API
    private fun peticionApi(){
        //Crear la peticion api para traer los datos del usuario loggeado
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

}