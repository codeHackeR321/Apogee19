package com.anenigmatic.apogee19.screens.menu.core

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anenigmatic.apogee19.screens.menu.data.StallsRepositoryImpl
import com.anenigmatic.apogee19.screens.menu.data.room.Stall
import com.anenigmatic.apogee19.screens.menu.view.StallListFragment
import com.anenigmatic.apogee19.screens.shared.util.asMut
import java.lang.Exception

class StallsViewModel(instance: StallListFragment) : ViewModel()
{
    var stallList : LiveData<List<Stall>> = MutableLiveData()
    var repository : StallsRepositoryImpl? = null
    var fragment = instance

    init
    {
        Log.d("Test" , "Entered init")
        try {
            repository = StallsRepositoryImpl()
        }
        catch (e : Exception)
        {
            repository = StallsRepositoryImpl().getInstance()
        }
    }

    fun getStallListFromServer()
    {
        Log.d("Test" , "Entered getStallList of view model")
        repository!!.getStalls(fragment.currentContext!! ,this)
    }

    fun loadDataFromCache()
    {
        repository!!.getCachedData(fragment.currentContext!! , this)
    }

    fun onStallDataAddedToLocalDatabase(list : List<Stall>)
    {
        Log.d("Test" , "onStallDataAdded called $list")
        stallList.asMut().value = list
    }

}