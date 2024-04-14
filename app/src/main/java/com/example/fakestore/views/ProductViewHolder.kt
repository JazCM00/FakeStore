package com.example.fakestore.views

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.R
import com.example.fakestore.model.modelProductResponse.ProductResponseItem
import com.squareup.picasso.Picasso

class ProductViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    private val txtName: TextView = view.findViewById(R.id.txtName)
    private val imgAvatar: ImageView = view.findViewById(R.id.imgAvatar)
    private val txtPrice: TextView = view.findViewById(R.id.txtPrice)
    fun render(product: ProductResponseItem){

        txtName.text = product.title
        txtPrice.text = product.price.toString() +"$"
        Picasso.get()
            .load(product.images[0])
            .error(R.mipmap.ic_launcher_round)
            .into(imgAvatar);
    }
}