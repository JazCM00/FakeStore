package com.example.fakestore.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.R
import com.example.fakestore.adapters.CategoryAdapter
import com.example.fakestore.adapters.UserAdapter
import com.example.fakestore.model.modelUserResponse.UserResponseItem
import com.example.fakestore.modelCategoryResponse.CategoryResponseItem
import com.example.fakestore.service.CategoryFactory
import com.example.fakestore.service.RetrofitServiceFactory
import kotlinx.coroutines.launch

class CategoryActivity : AppCompatActivity() {

    //Variable para depurar
    private val TAG = "CategoryActivity"

    // Almacenar
    private val categoryInit = mutableListOf<CategoryResponseItem>()
    private lateinit var btnVerMas: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        InitializationComponents()
        InitializationListener()
        //Capturamos los recyclers
        val rvCategory: RecyclerView = findViewById((R.id.rvCategoria))

        //Montamos el recycler de Usuarios
        val CategoryAdapter = CategoryAdapter(categoryInit)
        rvCategory.layoutManager = LinearLayoutManager(this)
        rvCategory.adapter = CategoryAdapter

        //Crear la peticion
        val apiCategoryService = CategoryFactory.getCategoryRetrofit()

        lifecycleScope.launch {
            //hacemos la peticion
            val data = apiCategoryService.getCategory("categories")
            //Borar los datos del userAdapter
            categoryInit.clear()
            categoryInit.addAll(data)
            //Repintar el recycleView
            CategoryAdapter.notifyDataSetChanged()
        }

    }
    //Funcion q Inicializa todos los componentes
    private fun InitializationComponents(){
        btnVerMas = findViewById<Button>(R.id.btnVerMas)
    }

    //Funcion que inicializa los Listener
    private fun InitializationListener(){
        //Ir a productos
        btnVerMas.setOnClickListener{
            goToProduct()
        }
    }

    private fun goToProduct(){
        val intent = Intent(this, ProductActivity::class.java)
        startActivity(intent)
        finish()
    }
}