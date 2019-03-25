package com.anenigmatic.apogee19.screens.events.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anenigmatic.apogee19.screens.events.data.EventRepository
import com.anenigmatic.apogee19.screens.shared.util.asMut
import com.anenigmatic.apogee19.screens.shared.util.set
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EventDetailsViewModel(eRepo: EventRepository, id: Long) : ViewModel() {

    val eventData: LiveData<Event> = MutableLiveData()


    private val d1 = CompositeDisposable()


    init {
        d1.set(eRepo.getEventById(id)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { event ->
                    eventData.asMut().postValue(event)
                },
                {

                }
            ))
    }


    override fun onCleared() {
        super.onCleared()
        d1.clear()
    }
}