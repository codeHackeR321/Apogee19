package com.anenigmatic.apogee19.screens.events.data

import com.anenigmatic.apogee19.screens.events.core.Event
import com.anenigmatic.apogee19.screens.events.core.Filter
import com.anenigmatic.apogee19.screens.events.data.retrofit.EventResponse
import com.anenigmatic.apogee19.screens.events.data.retrofit.EventsApi
import com.anenigmatic.apogee19.screens.events.data.room.EventsDao
import com.anenigmatic.apogee19.screens.events.data.storage.FilterStorage
import io.reactivex.Completable
import io.reactivex.Flowable
import org.threeten.bp.LocalDateTime

/**
 * This implementation of EventRepository aims to download the events data
 * successfully exactly once. If it  succeeds, the data  is not downloaded
 * again. If it fails, another attempt is made  when the data is requested
 * the next time. This process repeats till the data is downloaded.
 * */
class EventRepositoryImpl(
    private val fStorage: FilterStorage,
    private val eDao: EventsDao,
    private val eApi: EventsApi
) : EventRepository {

    private enum class FetchStatus {
        NotStarted, Started, Fetched, Failed
    }


    private var fetchStatus = FetchStatus.NotStarted


    override fun getFilteredEvents(): Flowable<List<Event>> {
        val localSource = fStorage.getFilter().switchMap { filter ->
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

        val freshSource = fetchAndUpdateEvents()
            .doOnSubscribe { fetchStatus = FetchStatus.Started }
            .doOnComplete { fetchStatus = FetchStatus.Fetched }
            .doOnError { fetchStatus = FetchStatus.Failed }
            .andThen(localSource)

        return if(fetchStatus.shouldAttemptToFetch()) { freshSource } else { localSource }
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


    // Fetches events from the backend and updates the local database with them.
    private fun fetchAndUpdateEvents(): Completable {
        return eApi.getAllEvents()
            .map { responseEvents ->
                responseEvents.map { responseEvent -> responseEvent.toEvent() }.filter { it.types.isNotEmpty() }
            }
            .doOnSuccess { events ->
                val oldIds = eDao.getAllEventIds()
                val newIds = events.map { it.id }

                eDao.deleteEventsByIds(oldIds.minus(newIds))

                val starredIds = eDao.getStarredEventIds()

                eDao.insertEvents(events)
                eDao.starEvents(starredIds, true)
            }
            .ignoreElement()
    }


    private fun EventResponse.toEvent(): Event {
        val datetime = LocalDateTime.parse(this.datetime.dropLast(1))
        val date = datetime.toLocalDate()
        val time = datetime.toLocalTime()

        // This is false because by default, events are not starred.
        val isStarred = false

        return Event(id, name, types.toSet(), setOf(spots), about, rules, date, time, duration, isStarred)
    }

    private fun FetchStatus.shouldAttemptToFetch(): Boolean {
        return this == FetchStatus.NotStarted || this == FetchStatus.Failed
    }

    private fun Event.isStarred(): Boolean {
        return isStarred
    }

    private fun Event.isOngoing(): Boolean {
        val now = LocalDateTime.now()
        return LocalDateTime.of(date, time) < now && now < LocalDateTime.of(date, time).plusMinutes(duration.toLong())
    }
}