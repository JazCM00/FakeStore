package com.example.fakestore.views

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.R
import com.example.fakestore.model.modelCategoryResponse.CategoryResponseItem

import com.squareup.picasso.Picasso

class CategoryViewHolder (view: View) : RecyclerView.ViewHolder(view) {


    private val txtName: TextView = view.findViewById(R.id.txtName)
    private val imgAvatar: ImageView = view.findViewById(R.id.imgAvatar)
    private val btnVermas:Button = view.findViewById(R.id.btnVerMas)

    fun render(category: CategoryResponseItem){

        txtName.text = category.name
        btnVermas.id = category.id

        Picasso.get()
            .load(category.image)
            .error(R.mipmap.ic_launcher_round)
            .into(imgAvatar);
    }
}