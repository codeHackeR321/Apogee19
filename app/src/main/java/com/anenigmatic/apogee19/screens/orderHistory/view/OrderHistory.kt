package com.anenigmatic.apogee19.screens.orderHistory.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anenigmatic.apogee19.R
import com.anenigmatic.apogee19.screens.orderHistory.core.OrderHistoryViewModel
import kotlinx.android.synthetic.main.fra_order_history.*
import kotlinx.android.synthetic.main.fra_order_history.view.*

class OrderHistory : Fragment() {

    private var currentContext : Context? = null
    lateinit var model: OrderHistoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fra_order_history, container, false)
        currentContext = view.context
        return view
    }

    override fun onStart() {

        model = OrderHistoryViewModel(this)
        model.getOrderListFromServer()
        model.orderList.observe(this, Observer {

            recyViewMenu.adapter = OrderHistoryAdapter(it , this)
            recyViewMenu.layoutManager = LinearLayoutManager(currentContext)
            recyViewMenu.adapter!!.notifyDataSetChanged()

        })

        super.onStart()
    }

    fun onOrderClicked(orderId : Int)
    {
        //To be implemented later
        model.getOrderListForOrder(orderId)
        OrderDetailDialog().apply { arguments = bundleOf("Order ID" to orderId) }.show(childFragmentManager , "OrderDetailDialog")
    }

}
