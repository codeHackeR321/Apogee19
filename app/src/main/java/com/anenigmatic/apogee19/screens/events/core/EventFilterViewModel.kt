package com.anenigmatic.apogee19.screens.events.core

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anenigmatic.apogee19.screens.events.data.EventRepository
import com.anenigmatic.apogee19.screens.shared.util.asMut
import com.anenigmatic.apogee19.screens.shared.util.set
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class EventFilterViewModel(private val eRepo: EventRepository) : ViewModel() {

    data class FilterOption(val name: String, val isActive: Boolean)


    val showOnlyOngoingEventsData: LiveData<Boolean> = MutableLiveData()
    val showOnlyStarredEventsData: LiveData<Boolean> = MutableLiveData()
    val typeFilterData: LiveData<List<FilterOption>> = MutableLiveData()
    val spotFilterData: LiveData<List<FilterOption>> = MutableLiveData()


    // This string is for use in filter lists for the "allow all" option.
    private val allowAllFilterName = "All"


    private val d1 = CompositeDisposable()
    private val d2 = CompositeDisposable()
    private val d3 = CompositeDisposable()


    init {
        d1.set(eRepo.getFilter()
            .subscribeOn(Schedulers.io())
            .subscribe(
                { filter ->
                    showOnlyOngoingEventsData.asMut().postValue(filter.showOnlyOngoing)
                    showOnlyStarredEventsData.asMut().postValue(filter.showOnlyStarred)
                },
                {

                }
            ))

        val combiner = BiFunction { t1: List<String>, t2: Filter -> Pair(t1, t2) }

        d2.set(Flowable.combineLatest(eRepo.getAllEventTypes(), eRepo.getFilter(), combiner)
            .map { pair ->
                val typeFilter = pair.second.type?: allowAllFilterName
                val types = ArrayList(pair.first).apply {
                    add(0, allowAllFilterName)
                }
                types.map { FilterOption(it, it == typeFilter) }
            }
            .subscribeOn(Schedulers.io())
            .subscribe(
                { typeFilter ->
                    typeFilterData.asMut().postValue(typeFilter)
                },
                {

                }
            ))

        d3.set(Flowable.combineLatest(eRepo.getAllEventSpots(), eRepo.getFilter(), combiner)
            .map { pair ->
                val spotFilter = pair.second.spot?: allowAllFilterName
                val spots = ArrayList(pair.first).apply {
                    add(0, allowAllFilterName)
                }
                spots.map { FilterOption(it, it == spotFilter) }
            }
            .subscribeOn(Schedulers.io())
            .subscribe(
                { spotFilter ->
                    spotFilterData.asMut().postValue(spotFilter)
                },
                {

                }
            ))
    }


    @SuppressLint("CheckResult")
    fun onSetTypeFilter(type: String) {
        eRepo.getFilter()
            .firstOrError()
            .flatMapCompletable { filter ->
                eRepo.setFilter(filter.copy(type = if(type == allowAllFilterName) { null } else { type }))
            }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {},{}
            )
    }

    @SuppressLint("CheckResult")
    fun onSetSpotFilter(spot: String) {
        eRepo.getFilter()
            .firstOrError()
            .flatMapCompletable { filter ->
                eRepo.setFilter(filter.copy(spot = if(spot == allowAllFilterName) { null } else { spot }))
            }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {},{}
            )
    }

    @SuppressLint("CheckResult")
    fun onSetShowOnlyOngoingEvents(value: Boolean) {
        eRepo.getFilter()
            .firstOrError()
            .flatMapCompletable { filter ->
                eRepo.setFilter(filter.copy(showOnlyOngoing = value))
            }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {},{}
            )
    }

    @SuppressLint("CheckResult")
    fun onSetShowOnlyStarredEvents(value: Boolean) {
        eRepo.getFilter()
            .firstOrError()
            .flatMapCompletable { filter ->
                eRepo.setFilter(filter.copy(showOnlyStarred = value))
            }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {},{}
            )
    }


    override fun onCleared() {
        super.onCleared()
        d1.clear()
        d2.clear()
        d3.clear()
    }
}