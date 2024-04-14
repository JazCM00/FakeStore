package com.example.fakestore.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.R
import com.example.fakestore.adapters.UserAdapter
import com.example.fakestore.model.modelUserResponse.UserResponseItem
import com.example.fakestore.service.RetrofitServiceFactory
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {
    //Variable para depurar
    private val TAG = "UserActivity"

    // Almacenar
    private val usersInit = mutableListOf<UserResponseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        Log.i(TAG,"**************************************************Entro en User")

        //Regreso al Menu
        val btnMenu: View = findViewById(R.id.fabMenu)
        btnMenu.setOnClickListener{
            Log.i(TAG,"**************************************************Salio en User")
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }

        //Capturamos los recyclers
        val rvUsuarios: RecyclerView = findViewById((R.id.rvUsuarios))

        //Montamos el recycler de Usuarios
        val userAdapter = UserAdapter(usersInit)
        rvUsuarios.layoutManager = LinearLayoutManager(this)
        rvUsuarios.adapter = userAdapter

        //Crear la peticion
        val apiUsersService = RetrofitServiceFactory.getUserRetrofit()

        lifecycleScope.launch {
            //hacemos la peticion
            val data = apiUsersService.getUsers("users")
            //Borar los datos del userAdapter
            usersInit.clear()
            usersInit.addAll(data)
            //Repintar el recycleView
            userAdapter.notifyDataSetChanged()
        }
    }
}