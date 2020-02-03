package com.example.android.appbarexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_layout.view.*

class RecyclerAdapter(val context: Context, var items : List<String>) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(){

    inner class MyViewHolder(view: View, var items: List<String>) : RecyclerView.ViewHolder(view) {

//        init {
//            view.imageDelete.setOnClickListener {
//                Toast.makeText(context, itemView.txvString.text.toString(), Toast.LENGTH_SHORT).show()
//////               items.filter{
//////                   it != items[adapterPosition]
//////               }
//                notifyItemRemoved(adapterPosition)
//                notifyItemChanged(adapterPosition, items.size - 1)
//                notifyDataSetChanged()
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false)
        return MyViewHolder(view, items)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.txvString.text = items[position]
    }

    fun updateList(newList: List<String>){
        items = newList
        notifyDataSetChanged()
    }
}