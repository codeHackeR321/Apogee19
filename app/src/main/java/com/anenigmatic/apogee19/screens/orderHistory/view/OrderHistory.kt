package com.anenigmatic.apogee19.screens.orderHistory.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.anenigmatic.apogee19.R
import kotlinx.android.synthetic.main.fra_order_history.*

class OrderHistory : Fragment() {

    private var currentContext : Context? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fra_order_history, container, false)
        currentContext = view.context
        return view
    }

    override fun onStart() {
        var list : ArrayList<String> = ArrayList(3)
        list.add("Hello")
        list.add("How are you")
        list.add("This is an array list")
        recyViewMenu.adapter = OrderHistoryAdapter(list , this)
        recyViewMenu.layoutManager = LinearLayoutManager(currentContext)
        recyViewMenu.adapter!!.notifyDataSetChanged()
        super.onStart()
    }


}
