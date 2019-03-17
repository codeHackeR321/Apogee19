package com.anenigmatic.apogee19.screens.events.data.retrofit

import com.squareup.moshi.Json

data class EventResponse(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "categories") val types: List<String>,
    @field:Json(name = "venue") val spots: String,
    @field:Json(name = "about") val about: String,
    @field:Json(name = "rules") val rules: String,
    @field:Json(name = "date_time") val datetime: String,
    @field:Json(name = "duration") val duration: Int = 0
)