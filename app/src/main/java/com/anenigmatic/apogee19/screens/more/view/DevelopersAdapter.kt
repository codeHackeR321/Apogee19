package com.anenigmatic.apogee19.screens.more.view

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anenigmatic.apogee19.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.row_developer.view.*

class DevelopersAdapter(private val dataset: ArrayList<Triple<String,String,String>>, private val context: Context): RecyclerView.Adapter<DevelopersAdapter.ViewHolder>(){

    private val options = RequestOptions.placeholderOf(R.drawable.ic_question_mark_button).error(R.drawable.ic_question_mark_button)

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view){

        var name: TextView = view.findViewById(R.id.nameLBL)
        var role: TextView = view.findViewById(R.id.nameLBL)
        var image: ImageView = view.findViewById(R.id.picIMG)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_developer, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text = dataset[position].first
        holder.role.text = dataset[position].second
        Glide.with(context).load(dataset[position].third).apply(options).into(holder.image)

    }


}