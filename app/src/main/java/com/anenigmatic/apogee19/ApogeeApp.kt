package com.anenigmatic.apogee19

import android.app.Application
import com.anenigmatic.apogee19.di.shared.AppComponent
import com.anenigmatic.apogee19.di.shared.AppModule
import com.anenigmatic.apogee19.di.shared.DaggerAppComponent
import com.anenigmatic.apogee19.screens.menu.data.MenuRepositoryImpl
import com.jakewharton.threetenabp.AndroidThreeTen

class ApogeeApp : Application() {

    companion object {

        lateinit var appComponent : AppComponent
        lateinit var menuRepository : MenuRepositoryImpl

    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        menuRepository = MenuRepositoryImpl(this)

        AndroidThreeTen.init(this)
    }
}