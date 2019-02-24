package com.anenigmatic.apogee19.screens.events.data.storage

import com.anenigmatic.apogee19.screens.events.core.Filter
import io.reactivex.Completable
import io.reactivex.Flowable

interface FilterStorage {

    fun getFilter(): Flowable<Filter>

    fun setFilter(filter: Filter): Completable
}