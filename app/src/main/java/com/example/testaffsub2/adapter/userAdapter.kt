package com.example.testaffsub2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testaffsub2.R
import com.example.testaffsub2.databinding.ItemBinding
import com.example.testaffsub2.model.Results

class UserAdapter(
    private val context: Context,
    private val users: ArrayList<Results>,
    private val onItemClick: OnItemClick
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    interface OnItemClick {
        fun onClick(user: Results)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val binding: ItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item, parent, false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        val user: Results = users[position]
        val name: String = user.name.first + " " + user.name.last
        holder.itemBinding.name.text = name
        Glide.with(context).load(user.picture.large)
            .apply(RequestOptions.circleCropTransform()).into(holder.itemBinding.avatar)
        holder.itemBinding.contentItem.setOnClickListener { onItemClick.onClick(user = user) }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun addUsers(user: ArrayList<Results>?) {
        user?.let { users.addAll(it) }
        notifyDataSetChanged()
    }


    inner class UserViewHolder(val itemBinding: ItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

}