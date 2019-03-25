package com.anenigmatic.apogee19.di.events

import com.anenigmatic.apogee19.screens.events.core.EventDetailsViewModelFactory
import com.anenigmatic.apogee19.screens.events.core.EventFilterViewModelFactory
import com.anenigmatic.apogee19.screens.events.core.EventListViewModelFactory
import dagger.Subcomponent

@Subcomponent
interface EventsComponent {

    fun inject(factory: EventListViewModelFactory)

    fun inject(factory: EventDetailsViewModelFactory)

    fun inject(factory: EventFilterViewModelFactory)
}