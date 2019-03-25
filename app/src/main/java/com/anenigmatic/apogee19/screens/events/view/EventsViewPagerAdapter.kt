package com.anenigmatic.apogee19.screens.events.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anenigmatic.apogee19.R
import com.anenigmatic.apogee19.screens.events.core.Event
import kotlinx.android.synthetic.main.pag_event_list.view.*

class EventsViewPagerAdapter(private val listener: ClickListener) :
    RecyclerView.Adapter<EventsViewPagerAdapter.EventViewPagerVHolder>(), EventsAdapter.ClickListener {

    interface ClickListener {

        fun onEventClicked(id: Long)

        fun onEventStarred(id: Long, value: Boolean)
    }


    private var eventLists = listOf<List<Event>>()


    fun setEvents(day0: List<Event>, day1: List<Event>, day2: List<Event>, day3: List<Event>) {
        eventLists = listOf(day0, day1, day2, day3)
        notifyDataSetChanged()
    }

    override fun getItemCount() = eventLists.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewPagerVHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EventViewPagerVHolder(inflater.inflate(R.layout.pag_event_list, parent, false), this)
    }

    override fun onBindViewHolder(holder: EventViewPagerVHolder, position: Int) {
        (holder.eventsRCY.adapter as EventsAdapter).submitList(eventLists[position])
    }

    override fun onEventClicked(id: Long) {
        listener.onEventClicked(id)
    }

    override fun onEventStarred(id: Long, value: Boolean) {
        listener.onEventStarred(id, value)
    }


    class EventViewPagerVHolder(rootPOV: View, listener: EventsAdapter.ClickListener) :
        RecyclerView.ViewHolder(rootPOV) {

        val eventsRCY: RecyclerView = rootPOV.eventsRCY.apply {
            adapter = EventsAdapter(listener)
        }
    }
}