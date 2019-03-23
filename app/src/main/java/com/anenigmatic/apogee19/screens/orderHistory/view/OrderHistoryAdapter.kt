package com.anenigmatic.apogee19.screens.orderHistory.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.anenigmatic.apogee19.R
import kotlinx.android.synthetic.main.row_order_list.view.*


class OrderHistoryAdapter(private var dataset : ArrayList<String>, private val fragment : OrderHistory) : RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var textViewOrderNo: TextView = view.findViewById(com.anenigmatic.apogee19.R.id.textViewOrderNo)
        var textViewStatus: TextView = view.findViewById(com.anenigmatic.apogee19.R.id.textViewStatus)
        var textViewOTP: TextView = view.findViewById(com.anenigmatic.apogee19.R.id.textViewOTP)
        var textViewTotal: TextView = view.findViewById(com.anenigmatic.apogee19.R.id.textViewTotal)
        var parents : ConstraintLayout = view.findViewById(R.id.diaPastOrderLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): OrderHistoryAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            com.anenigmatic.apogee19.R.layout.row_order_list,
            parent,
            false
        ) as View
        // set the view's size, margins, paddings and layout parameters

        return OrderHistoryAdapter.MyViewHolder(view)
    } // create a new view

    override fun getItemCount(): Int = dataset.size


    override fun onBindViewHolder(holder: OrderHistoryAdapter.MyViewHolder, position: Int) {

        holder.parents.setOnClickListener {
            fragment.onOrederClicked(dataset[position])
        }
    }

}