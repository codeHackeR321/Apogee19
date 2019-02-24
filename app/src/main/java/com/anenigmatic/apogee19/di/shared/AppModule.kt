package com.anenigmatic.apogee19.di.shared

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.anenigmatic.apogee19.screens.events.data.room.EventsDao
import com.anenigmatic.apogee19.screens.shared.data.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun providesEventsDao(appDatabase: AppDatabase): EventsDao {
        return appDatabase.getEventsDao()
    }

    @Singleton
    @Provides
    fun providesAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "apogee.db").build()
    }

    @Singleton
    @Provides
    fun providesSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("apogee.sp", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun providesApplication(): Application {
        return application
    }
}