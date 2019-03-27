package com.anenigmatic.apogee19.di.events

import com.anenigmatic.apogee19.screens.events.core.EventDetailsViewModelFactory
import com.anenigmatic.apogee19.screens.events.core.EventFilterViewModelFactory
import com.anenigmatic.apogee19.screens.events.core.EventListViewModelFactory
import com.anenigmatic.apogee19.screens.menu.core.CartViewModelFactory
import com.anenigmatic.apogee19.screens.menu.core.StallsViewModelFactory
import dagger.Subcomponent

@Subcomponent
interface EventsComponent {

    fun inject(factory: EventListViewModelFactory)

    fun inject(factory: EventDetailsViewModelFactory)

    fun inject(factory: EventFilterViewModelFactory)

    fun inject(factory: CartViewModelFactory)

    fun inject(factory: StallsViewModelFactory)
}