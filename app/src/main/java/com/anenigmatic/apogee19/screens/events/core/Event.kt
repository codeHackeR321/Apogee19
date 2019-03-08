package com.anenigmatic.apogee19.screens.events.core

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents an event in APOGEE.
 *
 * @property types  are the types(categories) of the event.
 * @property spots  are the spots where  event will happen.
 * @property duration  is duration of the event in minutes.
 * @property isStarred  tells whether the user has starred/bookmarked/favorited the event.
 * */
@Entity(tableName = "Events")
data class Event(
    @PrimaryKey val id: Long,
    val name: String,
    val types: Set<String>,
    val spots: Set<String>,
    val about: String,
    val rules: String,
    val date: LocalDate,
    val time: LocalTime,
    val duration: Int,
    val isStarred: Boolean
)