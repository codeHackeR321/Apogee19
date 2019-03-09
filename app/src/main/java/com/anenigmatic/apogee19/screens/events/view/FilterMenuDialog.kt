package com.anenigmatic.apogee19.screens.events.view

import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anenigmatic.apogee19.R
import com.anenigmatic.apogee19.screens.events.core.EventFilterViewModel
import com.anenigmatic.apogee19.screens.events.core.EventFilterViewModelFactory
import kotlinx.android.synthetic.main.dia_filter_menu.view.*

class FilterMenuDialog : DialogFragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, EventFilterViewModelFactory())[EventFilterViewModel::class.java]
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootPOV = inflater.inflate(R.layout.dia_filter_menu, container, false)

        rootPOV.closeBTN.setOnClickListener {
            dismiss()
        }

        viewModel.showOnlyOngoingEventsData.observe(viewLifecycleOwner, Observer { value ->
            when(value) {
                true  -> {
                    TextViewCompat.setTextAppearance(rootPOV.showOnlyOngoingToggleBTN, R.style.ActiveFilterButton)
                    rootPOV.showOnlyOngoingToggleBTN.setBackgroundResource(R.drawable.sh_rounded_rectangle_08dp)

                    val color = ContextCompat.getColor(requireContext(), R.color.vio07)
                    rootPOV.showOnlyOngoingToggleBTN.backgroundTintList = ColorStateList.valueOf(color)

                    rootPOV.showOnlyOngoingToggleBTN.setOnClickListener {
                        viewModel.onSetShowOnlyOngoingEvents(false)
                    }
                }
                false -> {
                    TextViewCompat.setTextAppearance(rootPOV.showOnlyOngoingToggleBTN, R.style.InactiveFilterButton)
                    rootPOV.showOnlyOngoingToggleBTN.setBackgroundResource(R.drawable.sh_rounded_rectangle_outline_08dp)

                    rootPOV.showOnlyOngoingToggleBTN.backgroundTintList = null

                    rootPOV.showOnlyOngoingToggleBTN.setOnClickListener {
                        viewModel.onSetShowOnlyOngoingEvents(true)
                    }
                }
            }
        })

        viewModel.showOnlyStarredEventsData.observe(viewLifecycleOwner, Observer { value ->
            when(value) {
                true  -> {
                    TextViewCompat.setTextAppearance(rootPOV.showOnlyStarredToggleBTN, R.style.ActiveFilterButton)
                    rootPOV.showOnlyStarredToggleBTN.setBackgroundResource(R.drawable.sh_rounded_rectangle_08dp)

                    val color = ContextCompat.getColor(requireContext(), R.color.vio07)
                    rootPOV.showOnlyStarredToggleBTN.backgroundTintList = ColorStateList.valueOf(color)

                    rootPOV.showOnlyStarredToggleBTN.setOnClickListener {
                        viewModel.onSetShowOnlyStarredEvents(false)
                    }
                }
                false -> {
                    TextViewCompat.setTextAppearance(rootPOV.showOnlyStarredToggleBTN, R.style.InactiveFilterButton)
                    rootPOV.showOnlyStarredToggleBTN.setBackgroundResource(R.drawable.sh_rounded_rectangle_outline_08dp)

                    rootPOV.showOnlyStarredToggleBTN.backgroundTintList = null

                    rootPOV.showOnlyStarredToggleBTN.setOnClickListener {
                        viewModel.onSetShowOnlyStarredEvents(true)
                    }
                }
            }
        })

        return rootPOV
    }

    // This method has been overriden to make the rounded corners visible.
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}