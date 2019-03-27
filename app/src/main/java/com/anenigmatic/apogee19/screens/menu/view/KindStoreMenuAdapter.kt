package com.anenigmatic.apogee19.screens.menu.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anenigmatic.apogee19.screens.menu.data.room.KIndStoreItemData

class KindStoreMenuAdapter(private var kindStoreMenuItems: List<KIndStoreItemData>): RecyclerView.Adapter<KindStoreMenuAdapter.ViewHolder>(){

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view){

        var nameLBL: TextView =view.findViewById(com.anenigmatic.apogee19.R.id.nameLBL)
        var infoLBL: TextView =view.findViewById(com.anenigmatic.apogee19.R.id.infoLBL)
        var starBTN : ImageView = view.findViewById(com.anenigmatic.apogee19.R.id.starBTN)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.anenigmatic.apogee19.R.layout.row_menu_item, parent,false) as View
        // set the view's size, margins, paddings and layout parameters

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = kindStoreMenuItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.nameLBL.text = kindStoreMenuItems[position].name
        holder.infoLBL.text = kindStoreMenuItems[position].price.toString()
        holder.starBTN.visibility = View.GONE
    }


}