package com.anenigmatic.apogee19.screens.events.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anenigmatic.apogee19.ApogeeApp
import com.anenigmatic.apogee19.screens.events.data.EventRepository
import javax.inject.Inject

class EventFilterViewModelFactory : ViewModelProvider.Factory {

    @Inject
    lateinit var eventRepository: EventRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        ApogeeApp.appComponent.newEventsComponent().inject(this)
        return EventFilterViewModel(eventRepository) as T
    }
}