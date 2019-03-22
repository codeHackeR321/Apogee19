package com.anenigmatic.apogee19.screens.orderHistory.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class OrderHistoryAdapter(private var dataset : ArrayList<String>, private val fragment : OrderHistory) : RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var nameLBL: TextView = view.findViewById(com.anenigmatic.apogee19.R.id.nameLBL)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            com.anenigmatic.apogee19.R.layout.row_menu,
            parent,
            false
        ) as View
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(view)
    } // create a new view

    override fun getItemCount(): Int = dataset.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.nameLBL.text = dataset[position]
    }
}