package com.anenigmatic.apogee19.screens.menu.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.anenigmatic.apogee19.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.dia_kind_store_cart.*
import kotlinx.android.synthetic.main.dia_kind_store_cart.view.*

class KindStoreDialog: DialogFragment(){

    var currentContext: Context? = null
    private val imgUrl by lazy {
        arguments!!.get("Url") as String
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dia_kind_store_cart, container, false)
        currentContext = view.context
        return view
    }

    override fun onStart() {
        super.onStart()

        kindDialog.minHeight = ((parentFragment!!.view!!.height)*0.85).toInt()
        kindDialog.minWidth = ((parentFragment!!.view!!.width)*0.85).toInt()// Height of dialog is 85% of the fragment width

        if (imgUrl == ""){
            Glide.with(currentContext!!).load(R.drawable.ic_question_mark_button).into(view!!.kindStoreImg)
        }
        else{
            Glide.with(currentContext!!).load(imgUrl).into(view!!.kindStoreImg)
        }


        view!!.closeDialog.setOnClickListener {
            dismiss()
        }
    }
}

