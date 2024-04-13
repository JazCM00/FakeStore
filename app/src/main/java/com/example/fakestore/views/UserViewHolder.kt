package com.example.fakestore.views

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.R
import com.example.fakestore.model.User
import com.example.fakestore.model.modelUserResponse.UserResponseItem
import com.squareup.picasso.Picasso

class UserViewHolder (view: View) :RecyclerView.ViewHolder(view) {

    private val txtEmail: TextView = view.findViewById(R.id.txtEmail)
    private val txtName: TextView = view.findViewById(R.id.txtName)
    private val txtRole: TextView = view.findViewById(R.id.txtRole)
    private val imgAvatar: ImageView = view.findViewById(R.id.imgAvatar)

    fun render(user: UserResponseItem){
        txtEmail.text = user.email
        txtName.text = user.name
        txtRole.text = user.role
        Picasso.get()
            .load(user.avatar)
            .error(R.mipmap.ic_launcher_round)
            .into(imgAvatar);
    }
}