package com.anenigmatic.apogee19.screens.events.data

import com.anenigmatic.apogee19.screens.events.core.Event
import com.anenigmatic.apogee19.screens.events.core.Filter
import com.anenigmatic.apogee19.screens.events.data.room.EventsDao
import com.anenigmatic.apogee19.screens.events.data.storage.FilterStorage
import io.reactivex.Completable
import io.reactivex.Flowable
import org.threeten.bp.LocalDateTime

class EventRepositoryImpl(private val fStorage: FilterStorage, private val eDao: EventsDao) : EventRepository {

    override fun getFilteredEvents(): Flowable<List<Event>> {
        return fStorage.getFilter().switchMap { filter ->
            when {
                filter.type == null && filter.spot == null -> eDao.getAllEvents()
                filter.type != null && filter.spot == null -> eDao.getAllEventsOfType(filter.type)
                filter.type == null && filter.spot != null -> eDao.getAllEventsOnSpot(filter.spot)
                filter.type != null && filter.spot != null -> eDao.getAllEventsOfTypeOnSpot(filter.type, filter.spot)
                else                                       -> throw IllegalStateException("Why filters make me cry?")
            }
                .map { events ->
                    if(filter.showOnlyOngoing) {
                        events.filter { it.isOngoing() }
                    } else {
                        events
                    }
                }
                .map { events ->
                    if(filter.showOnlyStarred) {
                        events.filter { it.isStarred() }
                    } else {
                        events
                    }
                }
        }
    }

    override fun getEventById(id: Long): Flowable<Event> {
        return eDao.getEventById(id)
    }

    override fun starEvent(id: Long, value: Boolean): Completable {
        return Completable.create { emitter ->
            try {
                eDao.starEvent(id, value)
                emitter.onComplete()
            } catch(e: Exception) {
                emitter.onError(e)
            }
        }
    }

    override fun getAllEventTypes(): Flowable<List<String>> {
        return eDao.getAllEventTypes()
            .map { joinedTypes ->
                joinedTypes.map { joinedType -> joinedType.split("<|>") }.flatten().distinct()
            }
    }

    override fun getAllEventSpots(): Flowable<List<String>> {
        return eDao.getAllEventSpots()
            .map { joinedTypes ->
                joinedTypes.map { joinedType -> joinedType.split("<|>") }.flatten().distinct()
            }
    }

    override fun getFilter(): Flowable<Filter> {
        return fStorage.getFilter()
    }

    override fun setFilter(filter: Filter): Completable {
        return fStorage.setFilter(filter)
    }


    private fun Event.isStarred(): Boolean {
        return isStarred
    }

    private fun Event.isOngoing(): Boolean {
        val now = LocalDateTime.now()
        return LocalDateTime.of(date, time) < now && now < LocalDateTime.of(date, time).plusMinutes(duration.toLong())
    }
}