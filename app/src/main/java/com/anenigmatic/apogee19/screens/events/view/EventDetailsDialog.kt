package com.anenigmatic.apogee19.screens.events.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anenigmatic.apogee19.R
import com.anenigmatic.apogee19.screens.events.core.EventDetailsViewModel
import com.anenigmatic.apogee19.screens.events.core.EventDetailsViewModelFactory
import kotlinx.android.synthetic.main.dia_event_details.view.*
import org.threeten.bp.format.DateTimeFormatter

class EventDetailsDialog : DialogFragment() {

    private val viewModel by lazy {
        val id = arguments!!.getLong("EVENT_ID")
        ViewModelProviders.of(this, EventDetailsViewModelFactory(id))[EventDetailsViewModel::class.java]
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootPOV = inflater.inflate(R.layout.dia_event_details, container, false)

        rootPOV.closeBTN.setOnClickListener {
            dismiss()
        }

        viewModel.eventData.observe(viewLifecycleOwner, Observer { event ->
            rootPOV.nameLBL.text = event.name

            val dayName = event.date.dayOfWeek.name.take(3).toUpperCase()
            val time = event.time.format(DateTimeFormatter.ofPattern("hh:mm a"))
            rootPOV.infoLBL.text = "$dayName $time, ${event.spots.joinToString(", ")}"

            rootPOV.typeLBL.text = "(CATEGORIES: ${event.types.joinToString(", ")})"

            rootPOV.aboutLBL.text = event.about
            rootPOV.rulesLBL.text = event.rules
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