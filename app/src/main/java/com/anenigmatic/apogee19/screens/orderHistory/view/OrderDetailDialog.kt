package com.anenigmatic.apogee19.screens.orderHistory.view

import android.content.Context
import android.graphics.PorterDuff
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
        diaPastOrderLayout.minHeight = ((parentFragment!!.view!!.height)*0.85).toInt()
        diaPastOrderLayout.minWidth = ((parentFragment!!.view!!.width)*0.85).toInt()
        closeDialog.setColorFilter(view!!.resources.getColor(R.color.yel03), PorterDuff.Mode.SRC_IN)
        var list : ArrayList<String> = ArrayList(3)
        list.add("Hello")
        list.add("How are you")
        list.add("This is an array list")
        recyViewCart.adapter = OrderDialogAdapter(list , this)
        recyViewCart.layoutManager = LinearLayoutManager(currentContext)
        recyViewCart.adapter!!.notifyDataSetChanged()
        super.onStart()
    }

}
