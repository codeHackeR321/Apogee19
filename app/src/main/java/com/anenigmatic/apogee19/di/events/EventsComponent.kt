package com.anenigmatic.apogee19.di.events

import com.anenigmatic.apogee19.screens.events.core.EventListViewModelFactory
import dagger.Subcomponent

@Subcomponent(modules = [EventsModule::class])
interface EventsComponent {

    fun inject(factory: EventListViewModelFactory)
}