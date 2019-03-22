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
import kotlinx.android.synthetic.main.dia_cart.view.*
import kotlinx.android.synthetic.main.try_menu_layout.*

class CartDialog: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.dia_cart, container, false)

        var trialList= ArrayList<String>()
        trialList.add("Curry of life")
        trialList.add("Double chicken roll")
        trialList.add("Double chicken roll with")
        trialList.add("Double chicken roll with extra ")
        trialList.add("Double chicken roll cheese")
        trialList.add("Double chicken roll with")
        trialList.add("Double chicken roll with extra ")
        trialList.add("Double chicken roll cheese")
        var cartAdapter= CartAdapter(trialList)
        view.recyViewCart.apply {
            adapter= cartAdapter
            layoutManager= LinearLayoutManager(view.context)
        }

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
}