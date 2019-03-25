package com.anenigmatic.apogee19.screens.events.data

import com.anenigmatic.apogee19.screens.events.core.Event
import com.anenigmatic.apogee19.screens.events.core.Filter
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Allows to access and modify all the relevant events data.
 * */
interface EventRepository {

    /**
     * Gives the list of events which are allowed by the currently active
     * filter. As the filter changes, new events are automatically given.
     * The events are ordered in ascending order by time.
     * */
    fun getFilteredEvents(): Flowable<List<Event>>

    /**
     * Gives the event having the passed-in id. It doesn't matter whether
     * it is allowed/not allowed by user's filters.
     * */
    fun getEventById(id: Long): Flowable<Event>

    /**
     * Stars/Un-stars the event having the given id.
     *
     * @param value  decides whether to star(true) or  un-star(false) the
     *      event.
     * */
    fun starEvent(id: Long, value: Boolean): Completable

    /**
     * Gives all the types of events known to the app. There's at least 1
     * event for each type given.
     * */
    fun getAllEventTypes(): Flowable<List<String>>

    /**
     * Gives all the spots of events known to the app. There's at least 1
     * event for each spot given.
     * */
    fun getAllEventSpots(): Flowable<List<String>>

    /**
     * Gives the filter  which is currently  being used to filter events.
     * */
    fun getFilter(): Flowable<Filter>

    /**
     * Sets the filter which is currently being used to filter events. It
     * automatically causes [getFilteredEvents] to emit a new event list.
     * */
    fun setFilter(filter: Filter): Completable
}