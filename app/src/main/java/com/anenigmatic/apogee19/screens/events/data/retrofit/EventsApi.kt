package com.anenigmatic.apogee19.screens.events.data.retrofit

import io.reactivex.Single
import retrofit2.http.GET

interface EventsApi {

    @GET("2019/registrations/events/")
    fun getAllEvents(): Single<List<EventResponse>>
}