package com.anenigmatic.apogee19.di.shared

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun providesApplication(): Application {
        return application
    }
}