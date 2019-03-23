package com.anenigmatic.apogee19.screens.menu.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.anenigmatic.apogee19.R
import kotlinx.android.synthetic.main.dia_cart.*
import kotlinx.android.synthetic.main.dia_cart.view.*
import kotlinx.android.synthetic.main.fra_stall_list.*

class CartDialog: DialogFragment() {

    var trialList= ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.dia_cart, container, false)


        trialList.add("Curry of life")
        trialList.add("Double chicken roll")
        trialList.add("Double chicken roll with")
        trialList.add("Double chicken roll with extra ")
        trialList.add("Double chicken roll cheese")
        trialList.add("Double chicken roll with")
        trialList.add("Double chicken roll with extra ")
        trialList.add("Double chicken roll cheese")
        var cartAdapter= CartAdapter(trialList , this)
        view.recyViewCart.apply {
            adapter= cartAdapter
            layoutManager= LinearLayoutManager(view.context)
        }

        view.closeDialog.setOnClickListener {
            dismiss()
        }

        return view
    }

    fun removeItem(item : String)
    {
        trialList.remove(item)
        view!!.recyViewCart.adapter!!.notifyDataSetChanged()
    }

    override fun onStart() {
        diaPastOrderLayout.minHeight = ((parentFragment!!.view!!.height)*0.85).toInt() // Height of dialog is 85% of the fragment width
        super.onStart()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}