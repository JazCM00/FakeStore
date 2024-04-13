package com.example.fakestore.activity

import android.os.Bundle
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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_category)

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
}