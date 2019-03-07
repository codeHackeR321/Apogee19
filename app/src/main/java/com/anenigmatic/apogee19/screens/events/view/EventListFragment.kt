package com.anenigmatic.apogee19.screens.events.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.anenigmatic.apogee19.R
import com.anenigmatic.apogee19.screens.events.core.Event
import com.anenigmatic.apogee19.screens.events.core.EventListViewModel
import com.anenigmatic.apogee19.screens.events.core.EventListViewModel.UiOrder
import com.anenigmatic.apogee19.screens.events.core.EventListViewModelFactory
import kotlinx.android.synthetic.main.fra_event_list.view.*
import org.threeten.bp.LocalDate

class EventListFragment : Fragment(), EventsViewPagerAdapter.ClickListener {

    private val viewModel by lazy {
        ViewModelProviders.of(this, EventListViewModelFactory())[EventListViewModel::class.java]
    }

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            makeDateBTNActive(position)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootPOV = inflater.inflate(R.layout.fra_event_list, container, false)

        rootPOV.eventsVPG.adapter = EventsViewPagerAdapter(this)

        rootPOV.day0BTN.setOnClickListener {
            rootPOV.eventsVPG.setCurrentItem(0, true)
        }

        rootPOV.day1BTN.setOnClickListener {
            rootPOV.eventsVPG.setCurrentItem(1, true)
        }

        rootPOV.day2BTN.setOnClickListener {
            rootPOV.eventsVPG.setCurrentItem(2, true)
        }

        rootPOV.day3BTN.setOnClickListener {
            rootPOV.eventsVPG.setCurrentItem(3, true)
        }

        rootPOV.retryBTN.setOnClickListener {
            rootPOV.searchTXT.setText("")
            viewModel.onSearchTextChanged("")
        }

        when(LocalDate.now().dayOfMonth) {
            28   -> rootPOV.day0BTN.setTextColor(ContextCompat.getColor(requireContext(), R.color.pnk01))
            29   -> rootPOV.day1BTN.setTextColor(ContextCompat.getColor(requireContext(), R.color.pnk01))
            30   -> rootPOV.day2BTN.setTextColor(ContextCompat.getColor(requireContext(), R.color.pnk01))
            31   -> rootPOV.day3BTN.setTextColor(ContextCompat.getColor(requireContext(), R.color.pnk01))
            else -> rootPOV.day0BTN.setTextColor(ContextCompat.getColor(requireContext(), R.color.pnk01))
        }

        rootPOV.eventsVPG.registerOnPageChangeCallback(pageChangeCallback)

        viewModel.orderData.observe(viewLifecycleOwner, Observer { order ->
            when(order) {
                is UiOrder.ShowLoadingState -> showLoadingState()
                is UiOrder.ShowWorkingState -> showWorkingState(order.day0Events, order.day1Events, order.day2Events, order.day3Events)
                is UiOrder.ShowFailureState -> showFailureState(order.message)
            }
        })

        viewModel.toastData.observe(viewLifecycleOwner, Observer { toast ->
            if(toast != null) {
                Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
            }
        })

        return rootPOV
    }

    override fun onEventClicked(id: Long) {

    }

    override fun onEventStarred(id: Long, value: Boolean) {
        viewModel.starEvent(id, value)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        view?.let { view ->
            view.eventsVPG.unregisterOnPageChangeCallback(pageChangeCallback)
        }
    }

    private fun showLoadingState() {
        view?.let { view ->
            view.loaderPBR.visibility = View.VISIBLE
            view.errorMessageLBL.visibility = View.GONE
            view.retryBTN.visibility = View.GONE

            (view.eventsVPG.adapter as EventsViewPagerAdapter).setEvents(
                listOf(), listOf(), listOf(), listOf()
            )
        }

    }

    private fun showWorkingState(day0Events: List<Event>, day1Events: List<Event>, day2Events: List<Event>, day3Events: List<Event>) {
        view?.let { view ->
            view.loaderPBR.visibility = View.GONE
            view.errorMessageLBL.visibility = View.GONE
            view.retryBTN.visibility = View.GONE

            (view.eventsVPG.adapter as EventsViewPagerAdapter).setEvents(
                day0Events, day1Events, day2Events, day3Events
            )
        }
    }

    private fun showFailureState(message: String) {
        view?.let { view ->
            view.loaderPBR.visibility = View.GONE
            view.errorMessageLBL.visibility = View.VISIBLE
            view.retryBTN.visibility = View.VISIBLE

            view.errorMessageLBL.text = message
            (view.eventsVPG.adapter as EventsViewPagerAdapter).setEvents(
                listOf(), listOf(), listOf(), listOf()
            )
        }
    }

    private fun makeDateBTNActive(position: Int) {
        view?.let { view ->
            listOf(view.day0BTN, view.day1BTN, view.day2BTN, view.day3BTN).forEach { btn ->
                btn.setTextColor(ContextCompat.getColor(requireContext(), R.color.vio06))
            }

            when(position) {
                0 -> view.day0BTN.setTextColor(ContextCompat.getColor(requireContext(), R.color.pnk01))
                1 -> view.day1BTN.setTextColor(ContextCompat.getColor(requireContext(), R.color.pnk01))
                2 -> view.day2BTN.setTextColor(ContextCompat.getColor(requireContext(), R.color.pnk01))
                3 -> view.day3BTN.setTextColor(ContextCompat.getColor(requireContext(), R.color.pnk01))
            }
        }
    }
}