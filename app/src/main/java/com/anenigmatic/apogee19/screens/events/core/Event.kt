package com.anenigmatic.apogee19.screens.events.core

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

/**
 * Represents an event in APOGEE.
 *
 * @property type  is the type(category)  of the event.
 * @property spot  is the spot where event will happen.
 * @property duration  is event's  duration in minutes.
 * @property isStarred  tells whether the user has starred/bookmarked/favorited the event.
 * */
data class Event(
    val id: Long,
    val name: String,
    val type: String,
    val spot: String,
    val about: String,
    val rules: String,
    val date: LocalDate,
    val time: LocalTime,
    val duration: Int,
    val isStarred: Boolean
)