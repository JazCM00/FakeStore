package com.example.fakestore.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.R
import com.example.fakestore.adapters.ProductAdapter
import com.example.fakestore.model.modelProductResponse.ProductResponseItem
import com.example.fakestore.service.ProductFactory
import kotlinx.coroutines.launch

class ProductActivity : AppCompatActivity() {
    //Variable para depurar
    private val TAG = "ProductActivity"

    // Almacenar
    private val productInit = mutableListOf<ProductResponseItem>()
    //Variables de los componentes
    private lateinit var btnVerMas: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        //Capturamos los recyclers
        val rvProduct: RecyclerView = findViewById((R.id.rvProducto))

        //Montamos el recycler de categoria
        val productAdapter = ProductAdapter(productInit)
        rvProduct.layoutManager = LinearLayoutManager(this)
        rvProduct.adapter = productAdapter

        //Crear la peticion
        val apiProductService = ProductFactory.getProduct()

        lifecycleScope.launch {
            //hacemos la peticion
            val data = apiProductService.getCategory("3")
            //Borar los datos del userAdapter
            productInit.clear()
            productInit.addAll(data)
            //Repintar el recycleView
            productAdapter.notifyDataSetChanged()
        }

    }


}