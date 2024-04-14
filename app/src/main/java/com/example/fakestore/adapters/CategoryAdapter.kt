package com.example.fakestore.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.R
import com.example.fakestore.activity.ProductActivity
import com.example.fakestore.model.modelCategoryResponse.CategoryResponseItem
import com.example.fakestore.util.Variables
import com.example.fakestore.views.CategoryViewHolder

class CategoryAdapter(private val category: List<CategoryResponseItem>) :
    RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.lista_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount() = category.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        //Variable para depurar
        val TAG = "CategoryActivity"

        holder.render(category[position])
        holder.btnVerMas.setOnClickListener {
            Variables.idCategoria = holder.id
            Variables.categoryName = holder.name
            val intent = Intent(holder.itemView.context, ProductActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }
}