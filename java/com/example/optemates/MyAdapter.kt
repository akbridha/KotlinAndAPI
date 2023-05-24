package com.example.optemates

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val myData : ArrayList<Pair<String , String>>, private val context: Context):
    RecyclerView.Adapter<MyAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {



//        val tv1: TextView = itemView.findViewById(R.id.title)
//        val tv2: TextView = itemView.findViewById(R.id.description)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return  ListViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(judul,deskripsi) = myData[position]
//        holder.tv1.text = judul
//        holder.tv2.text = deskripsi

        holder.itemView.setOnClickListener {
            Toast.makeText(context,judul+" : "+context, Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount(): Int {
        return myData.size
    }
}