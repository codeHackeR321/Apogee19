package com.anenigmatic.apogee19.screens.events.view

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anenigmatic.apogee19.R
import com.anenigmatic.apogee19.screens.events.core.Event
import kotlinx.android.synthetic.main.row_event.view.*
import org.threeten.bp.format.DateTimeFormatter

class EventsAdapter(private val listener: ClickListener) : ListAdapter<Event, EventsAdapter.EventVHolder>(DiffCallback()) {

    interface ClickListener {

        fun onEventClicked(id: Long)

        fun onEventStarred(id: Long, value: Boolean)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventVHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EventVHolder(inflater.inflate(R.layout.row_event, parent, false))
    }

    override fun onBindViewHolder(holder: EventVHolder, position: Int) {
        val event = getItem(position)

        holder.nameLBL.text = event.name

        val dayName = event.date.dayOfWeek.name.take(3).toUpperCase()
        val time = event.time.format(DateTimeFormatter.ofPattern("hh:mm a"))

        holder.infoLBL.text = "$dayName $time, ${event.spots.joinToString(", ")}"

        when(event.isStarred) {
            true  -> {
                holder.starBTN.setImageResource(R.drawable.ic_bookmark_filled)
                val color = ContextCompat.getColor(holder.rootPOV.context, R.color.vio07)
                ImageViewCompat.setImageTintList(holder.starBTN, ColorStateList.valueOf(color))
            }
            false -> {
                holder.starBTN.setImageResource(R.drawable.ic_bookmark_outlined)
                val color = ContextCompat.getColor(holder.rootPOV.context, R.color.vio04)
                ImageViewCompat.setImageTintList(holder.starBTN, ColorStateList.valueOf(color))
            }
        }

        holder.starBTN.setOnClickListener {
            listener.onEventStarred(event.id, !event.isStarred)
        }

        holder.rootPOV.setOnClickListener {
            listener.onEventClicked(event.id)
        }
    }


    class EventVHolder(val rootPOV: View) : RecyclerView.ViewHolder(rootPOV) {

        val nameLBL: TextView = rootPOV.nameLBL
        val infoLBL: TextView = rootPOV.infoLBL
        val starBTN: ImageView = rootPOV.starBTN
    }


    class DiffCallback : DiffUtil.ItemCallback<Event>() {

        override fun areItemsTheSame(oldItem: Event, newItem: Event) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.name == newItem.name &&
                    oldItem.spots == newItem.spots &&
                    oldItem.date == newItem.date &&
                    oldItem.time == newItem.time &&
                    oldItem.isStarred == newItem.isStarred
        }
    }
}