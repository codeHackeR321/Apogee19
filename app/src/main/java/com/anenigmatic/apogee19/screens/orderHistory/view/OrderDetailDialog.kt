package com.anenigmatic.apogee19.screens.orderHistory.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.anenigmatic.apogee19.R
import kotlinx.android.synthetic.main.dia_cart.view.*
import kotlinx.android.synthetic.main.dia_past_order_detail.*

class OrderDetailDialog : DialogFragment() {

    private var currentContext : Context? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.dia_past_order_detail, container, false)
        currentContext = view.context

        view.closeDialog.setOnClickListener {
            dismiss()
        }
        return view
    }

    override fun onStart() {
        var list : ArrayList<String> = ArrayList(3)
        list.add("Hello")
        list.add("How are you")
        list.add("This is an array list")
        recyViewCart.adapter = OrderDetailAdapter(list , this)
        recyViewCart.layoutManager = LinearLayoutManager(currentContext)
        recyViewCart.adapter!!.notifyDataSetChanged()
        super.onStart()
    }

}
