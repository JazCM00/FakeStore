package com.example.fakestore.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.R
import com.example.fakestore.adapters.CategoryAdapter
import com.example.fakestore.model.modelCategoryResponse.CategoryResponseItem

import com.example.fakestore.service.CategoryFactory
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


        Log.i(TAG, "**************************************************Entro en Categoria")
        //Regreso al Menu
        val btnMenu: View = findViewById(R.id.fabMenu)
        btnMenu.setOnClickListener {
            Log.i(TAG, "**************************************************Salio de categoria")
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
        Log.i(TAG,"**************************************************Paso 1")
        //Capturamos los recyclers
        val rvCategory: RecyclerView = findViewById((R.id.rvCategoria))

        //Montamos el recycler de Usuarios
        val CategoryAdapter = CategoryAdapter(categoryInit)
        rvCategory.layoutManager = LinearLayoutManager(this)
        rvCategory.adapter = CategoryAdapter
        Log.i(TAG,"**************************************************Paso 2")
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
}