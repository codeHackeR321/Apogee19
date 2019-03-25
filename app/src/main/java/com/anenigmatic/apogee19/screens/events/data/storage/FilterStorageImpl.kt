package com.anenigmatic.apogee19.screens.events.data.storage

import android.content.SharedPreferences
import androidx.core.content.edit
import com.anenigmatic.apogee19.screens.events.core.Filter
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject

class FilterStorageImpl(private val prefs: SharedPreferences) : FilterStorage {

    private val filterSubject = BehaviorSubject.create<Filter>()

    init {
        val type = prefs.getString("TYPE", null)
        val spot = prefs.getString("SPOT", null)
        val showOnlyOngoing = prefs.getBoolean("SHOW_ONLY_ONGOING", false)
        val showOnlyStarred = prefs.getBoolean("SHOW_ONLY_STARRED", false)
        
        filterSubject.onNext(Filter(type, spot, showOnlyOngoing, showOnlyStarred))
    }

    override fun getFilter(): Flowable<Filter> {
        return filterSubject.toFlowable(BackpressureStrategy.LATEST)
            .distinctUntilChanged()
    }

    override fun setFilter(filter: Filter): Completable {
        return Completable.create { emitter ->
            try {
                prefs.edit(commit = true) {
                    putString("TYPE", filter.type)
                    putString("SPOT", filter.spot)
                    putBoolean("SHOW_ONLY_ONGOING", filter.showOnlyOngoing)
                    putBoolean("SHOW_ONLY_STARRED", filter.showOnlyStarred)
                }

                emitter.onComplete()
                filterSubject.onNext(filter)
            } catch(e: Exception) {
                emitter.onError(e)
            }
        }
    }
}