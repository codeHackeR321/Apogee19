package com.anenigmatic.apogee19.screens.orderHistory.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anenigmatic.apogee19.ApogeeApp
import com.anenigmatic.apogee19.R
import com.anenigmatic.apogee19.screens.menu.data.room.OrderItem
import com.anenigmatic.apogee19.screens.menu.data.room.StallItem
import kotlinx.android.synthetic.main.dia_cart.view.*
import kotlinx.android.synthetic.main.dia_past_order_detail.*

class OrderDetailDialog : DialogFragment() {

    private var currentContext : Context? = null
    private val orderId by lazy {
        arguments!!.get("Order ID") as Int
    }
    private val orderItems by lazy {
        arguments!!.getStringArrayList("Order List")!!.map { str ->
            val strs = str.split("<|>")
            OrderItem(0, 0, 0, strs[0].toInt(), strs[1], strs[2].toInt(), strs[3].toInt())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.dia_past_order_detail, container, false)
        currentContext = view.context

        view.closeDialog.setOnClickListener {
            dismiss()
        }
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onStart() {
        diaPastOrderLayout.minHeight = ((parentFragment!!.view!!.height)*0.85).toInt()
        diaPastOrderLayout.minWidth = ((parentFragment!!.view!!.width)*0.85).toInt()
        closeDialog.setColorFilter(view!!.resources.getColor(R.color.yel03), PorterDuff.Mode.SRC_IN)

        //items.sortedBy { it.stallId }
        recyViewCart.adapter = OrderDialogAdapter(orderItems)
        Log.d("Test","orderItems: $orderItems")
        recyViewCart.layoutManager = LinearLayoutManager(currentContext)
        recyViewCart.adapter!!.notifyDataSetChanged()

        super.onStart()
    }

}
