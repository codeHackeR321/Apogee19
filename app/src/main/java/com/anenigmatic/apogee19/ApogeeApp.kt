package com.anenigmatic.apogee19

import android.app.Application
import com.anenigmatic.apogee19.di.shared.AppComponent
import com.anenigmatic.apogee19.di.shared.AppModule
import com.anenigmatic.apogee19.di.shared.DaggerAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen

class ApogeeApp : Application() {

    companion object {

        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        AndroidThreeTen.init(this)
    }
}