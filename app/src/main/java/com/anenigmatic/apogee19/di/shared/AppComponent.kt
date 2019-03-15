package com.anenigmatic.apogee19.di.shared

import com.anenigmatic.apogee19.di.events.EventsComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun newEventsComponent(): EventsComponent
}