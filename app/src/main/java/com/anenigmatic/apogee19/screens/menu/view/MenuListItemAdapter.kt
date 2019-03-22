package com.anenigmatic.apogee19.screens.menu.view



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuListItemAdapter(private var dataset :ArrayList<String>) : RecyclerView.Adapter<MenuListItemAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){

        var nameLBL: TextView =view.findViewById(com.anenigmatic.apogee19.R.id.nameLBL)


    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MenuListItemAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.anenigmatic.apogee19.R.layout.row_menu, parent,false) as View
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(view)
    } // create a new view

    override fun getItemCount(): Int = dataset.size


    override fun onBindViewHolder(holder: MenuListItemAdapter.MyViewHolder, position: Int) {

        /**
         * Since recycler view works in a way such that it only recycles the information that is displayed in the views
         * of the recycler view, images of groups are also visible as currently we had not specified what image to overwrite
         * the previous image
         */

        holder.nameLBL.text =  dataset[position]



    }


}