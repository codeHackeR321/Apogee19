package com.anenigmatic.apogee19.screens.shared.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anenigmatic.apogee19.screens.events.core.Event
import com.anenigmatic.apogee19.screens.events.data.room.EventsDao

@Database(entities = [Event::class], version = 1, exportSchema = true)
@TypeConverters(AppTypeConverters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getEventsDao(): EventsDao
}