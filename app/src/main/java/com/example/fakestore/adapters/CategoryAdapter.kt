package com.example.fakestore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.R
import com.example.fakestore.model.modelCategoryResponse.CategoryResponseItem
import com.example.fakestore.views.CategoryViewHolder

class CategoryAdapter (private val category: List<CategoryResponseItem>): RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_category,parent,false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount() = category.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.render(category [position])
    }
}