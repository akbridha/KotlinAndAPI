package com.example.optemates

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.optemates.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>()
   {
       private val  list = ArrayList<User>()
       private  var onItemClickCallback: OnitemCLickCallback? = null
       fun  setOnItemClickCallback(onItemClickCallback: OnitemCLickCallback){
           this.onItemClickCallback = onItemClickCallback
       }
       fun setList(users: ArrayList<User>){
           list.clear()
           list.addAll(users)
           notifyDataSetChanged()
       }
       inner  class UserViewHolder (val binding : ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
           fun bind(user : User){
               binding.root.setOnClickListener{
                   Log.d("hasil click RV", it.toString())
                   onItemClickCallback?.onItemClicked(user)
               }
               binding.apply {
               Glide.with(itemView)
                   .load(user.avatar_url)
                   .transition(DrawableTransitionOptions.withCrossFade())
                   .centerCrop()
                   .into(ivUser)
               tvUsername.text = user.login
               }
           }
       }
       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
           val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
           return  UserViewHolder(view)
       }
       override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
           holder.bind(list[position])
       }
       override fun getItemCount(): Int {
          return list.size
       }
       interface  OnitemCLickCallback{
           fun onItemClicked(data: User)
       }
   }