package com.anenigmatic.apogee19.di.shared

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.anenigmatic.apogee19.screens.events.data.EventRepository
import com.anenigmatic.apogee19.screens.events.data.EventRepositoryImpl
import com.anenigmatic.apogee19.screens.events.data.retrofit.EventsApi
import com.anenigmatic.apogee19.screens.events.data.room.EventsDao
import com.anenigmatic.apogee19.screens.events.data.storage.FilterStorage
import com.anenigmatic.apogee19.screens.events.data.storage.FilterStorageImpl
import com.anenigmatic.apogee19.screens.menu.data.MenuRepository
import com.anenigmatic.apogee19.screens.menu.data.MenuRepositoryImpl
import com.anenigmatic.apogee19.screens.shared.data.retrofit.BaseInterceptor
import com.anenigmatic.apogee19.screens.shared.data.room.AppDatabase
import com.anenigmatic.apogee19.screens.shared.util.NetworkDetails
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun providesMenuRepository(application: Application): MenuRepository {
        return MenuRepositoryImpl(application)
    }

    @Singleton
    @Provides
    fun providesEventRepository(filterStorage: FilterStorage, eventsDao: EventsDao, eventsApi: EventsApi, networkDetails: NetworkDetails): EventRepository {
        return EventRepositoryImpl(filterStorage, eventsDao, eventsApi, networkDetails)
    }

    @Singleton
    @Provides
    fun providesEventsApi(retrofit: Retrofit): EventsApi {
        return retrofit.create(EventsApi::class.java)
    }

    @Singleton
    @Provides
    fun providesEventsDao(appDatabase: AppDatabase): EventsDao {
        return appDatabase.getEventsDao()
    }

    @Singleton
    @Provides
    fun providesFilterStorage(sharedPreferences: SharedPreferences): FilterStorage {
        return FilterStorageImpl(sharedPreferences)
    }

    @Singleton
    @Provides
    fun providesNetworkDetails(application: Application): NetworkDetails {
        return NetworkDetails(application)
    }

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://bits-apogee.org/2019/")
            .client(OkHttpClient().newBuilder().addInterceptor(BaseInterceptor()).build())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
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