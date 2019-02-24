package com.anenigmatic.apogee19.screens.events.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anenigmatic.apogee19.screens.events.core.Event
import io.reactivex.Flowable

@Dao
interface EventsDao {

    @Query("SELECT * FROM Events ORDER BY date ASC, time ASC")
    fun getAllEvents(): Flowable<List<Event>>

    @Query("SELECT * FROM Events WHERE type = :type ORDER BY date ASC, time ASC")
    fun getAllEventsOfType(type: String): Flowable<List<Event>>

    @Query("SELECT * FROM Events WHERE spot = :spot ORDER BY date ASC, time ASC")
    fun getAllEventsOnSpot(spot: String): Flowable<List<Event>>

    @Query("SELECT * FROM Events WHERE type = :type AND spot = :spot ORDER BY date ASC, time ASC")
    fun getAllEventsOfTypeOnSpot(type: String, spot: String): Flowable<List<Event>>

    @Query("SELECT * FROM Events WHERE id = :id")
    fun getEventById(id: Long): Flowable<Event>

    @Query("SELECT DISTINCT type FROM Events")
    fun getAllEventTypes(): Flowable<List<String>>

    @Query("SELECT DISTINCT spot FROM Events")
    fun getAllEventSpots(): Flowable<List<String>>

    @Query("SELECT id FROM Events")
    fun getAllEventIds(): List<Long>

    @Query("SELECT id FROM Events WHERE isStarred = 1")
    fun getStarredEventIds(): List<Long>

    @Query("SELECT COUNT(id) FROM Events")
    fun getEventCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(events: List<Event>)

    @Query("UPDATE Events SET isStarred = :value WHERE id = :id")
    fun starEvent(id: Long, value: Boolean)

    @Query("UPDATE Events SET isStarred = :value WHERE id in (:ids)")
    fun starEvents(ids: List<Long>, value: Boolean)

    @Query("DELETE FROM Events WHERE id IN (:ids)")
    fun deleteEventsByIds(ids: List<Long>)
}