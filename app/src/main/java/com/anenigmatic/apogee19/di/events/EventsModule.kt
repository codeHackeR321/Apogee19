package com.anenigmatic.apogee19.di.events

import android.content.SharedPreferences
import com.anenigmatic.apogee19.screens.events.data.EventRepository
import com.anenigmatic.apogee19.screens.events.data.EventRepositoryImpl
import com.anenigmatic.apogee19.screens.events.data.room.EventsDao
import com.anenigmatic.apogee19.screens.events.data.storage.FilterStorage
import com.anenigmatic.apogee19.screens.events.data.storage.FilterStorageImpl
import dagger.Module
import dagger.Provides

@Module
class EventsModule {

    @Provides
    fun providesEventRepository(filterStorage: FilterStorage, eventsDao: EventsDao): EventRepository {
        return EventRepositoryImpl(filterStorage, eventsDao)
    }

    @Provides
    fun providesFilterStorage(sharedPreferences: SharedPreferences): FilterStorage {
        return FilterStorageImpl(sharedPreferences)
    }
}