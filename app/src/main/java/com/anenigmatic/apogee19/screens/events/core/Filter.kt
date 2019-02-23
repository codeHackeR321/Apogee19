package com.anenigmatic.apogee19.screens.events.core

/**
 * Represents a filter which the user uses to filter events.
 *
 * @property type  is the type of  events the user  wants to see. Events having different
 *      types will be blocked. If null, events won't be filtered on the basis of types.
 * @property spot  is the spot where to user wants to see events. Events having different
 *      spots will be blocked. If null, events won't be filtered on the basis of spots.
 * @property showOnlyOngoing  will only allow those events which are currently  going on.
 * @property showOnlyStarred  will only allow those events which are starred by the user.
 * */
data class Filter(val type: String?, val spot: String?, val showOnlyOngoing: Boolean, val showOnlyStarred: Boolean)