package com.anenigmatic.apogee19.screens.menu.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.anenigmatic.apogee19.R
import com.anenigmatic.apogee19.screens.menu.data.room.KIndStoreItemData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fra_stall_list.view.*

class KindStoreMenuAdapter(private var kindStoreMenuItems: List<KIndStoreItemData>, private var baseView: View, private var context: Context, private var fragment: StallListFragment): RecyclerView.Adapter<KindStoreMenuAdapter.ViewHolder>(){

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view){

        var nameLBL: TextView =view.findViewById(com.anenigmatic.apogee19.R.id.nameLBL)
        var infoLBL: TextView =view.findViewById(com.anenigmatic.apogee19.R.id.infoLBL)
        var starBTN : ImageView = view.findViewById(com.anenigmatic.apogee19.R.id.starBTN)
        var parent: ConstraintLayout = view.findViewById(com.anenigmatic.apogee19.R.id.rootPOV)
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
        holder.parent.setOnClickListener {

            if (kindStoreMenuItems[position].image == null){
                fragment.kindStoreImg("")
            }else{
                fragment.kindStoreImg(kindStoreMenuItems[position].image!!)
            }


        }
    }
}