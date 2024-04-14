package com.example.fakestore.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.fakestore.R
import com.example.fakestore.service.ApiService
import com.example.fakestore.service.response.LoginResponse
import com.example.fakestore.util.LoadingDialog
import com.example.fakestore.util.PreferenceHelper
import com.example.fakestore.util.PreferenceHelper.PreferenceHelper.set
import com.example.fakestore.util.Variables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    //Variable para depurar
    private val TAG = "Mainactivity"

    //Variables del Splash
    private var splashScreenStays: Boolean = true;
    private var DELAY:Long = 1500L;

    //Variable para la API
    private val apiService: ApiService by lazy {
        ApiService.create()
    }
    private var accessToken: String = ""

    //Variables de los componentes
    private lateinit var btnRegister: TextView
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        //Variables
        val screensplash = installSplashScreen()

        //Arranque de la pantalla Login
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Condiciones del Splash
        screensplash.setKeepOnScreenCondition{splashScreenStays}
        Handler(Looper.getMainLooper()).postDelayed({ splashScreenStays = false}, DELAY)

        //Inicializacion
        InitializationComponents()
        InitializationListener()

    }
    //Funcion q Inicializa todos los componentes
    private fun InitializationComponents(){
        btnLogin = findViewById<Button>(R.id.btnLogin)
    }

    //Funcion que inicializa los Listener
    private fun InitializationListener(){

        //Cambio a la pantalla Menu
        btnLogin.setOnClickListener{
            validateLogin()
        }
    }

    //Funcion para validar con la Api
    private fun validateLogin(){
        val txtEmail = findViewById<TextView>(R.id.txtEmail).text.toString()
        val txtPassword = findViewById<TextView>(R.id.txtPassword).text.toString()

        if(txtEmail.trim().isEmpty() || txtPassword.trim().isEmpty()){

            Toast.makeText(applicationContext, "DEBE INGRESAR EL USUARIO Y LA CONTRASENA", Toast.LENGTH_SHORT).show()
            return
        }

        val call = apiService.postLogin(txtEmail, txtPassword)
        call.enqueue(object: Callback<LoginResponse>{

            //Respuesta correcta
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val messageLogin = response.message()
                val loginResponse = response.body()

                //Respuesta es nula
                if(loginResponse == null){
                    if(messageLogin == "Unauthorized") {

                        Toast.makeText(applicationContext, "CREDENCIALES INCORRECTAS", Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(applicationContext, "ERROR SERVIDOR", Toast.LENGTH_SHORT).show()
                    }
                }else {
                    if (messageLogin == "Created"){
                        accessToken = response.body()!!.access_token
                        createSession()
                        goToMenu()
                    }else {
                        Toast.makeText(applicationContext, "ERROR SERVIDOR", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            //Respuesta erronea
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "ERROR SERVIDOR", Toast.LENGTH_SHORT).show()
            }

        } )
    }

    //Funcion para abrir el menu
    private fun goToMenu(){
        Log.i(TAG,"**************************************************Entro en Menu")
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("access_token",accessToken)
        Variables.tokenGlobal = accessToken
        startActivity(intent)
        finish()
    }

    //Funcion para crear la session
    private fun createSession(){
        val preferences = PreferenceHelper.PreferenceHelper.defaultPrefs(this)
        preferences["session"] = true
    }

}