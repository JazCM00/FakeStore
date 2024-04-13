package com.example.fakestore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.R
import com.example.fakestore.model.User
import com.example.fakestore.model.modelUserResponse.UserResponseItem
import com.example.fakestore.views.UserViewHolder

class UserAdapter (private val users: List<UserResponseItem>): RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_user,parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.render(users [position])
    }
}