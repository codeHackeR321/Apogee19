package com.anenigmatic.apogee19.screens.menu.core

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anenigmatic.apogee19.screens.menu.data.StallsRepositoryImpl
import com.anenigmatic.apogee19.screens.menu.data.room.Stall
import com.anenigmatic.apogee19.screens.menu.data.room.StallItem
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

    /**
     * Makes the REST Api call through the repository to get new data regarding the list of stalls and their menus
     */
    fun getStallListFromServer()
    {
        Log.d("Test" , "Entered getStallList of view model")
        repository!!.getStalls(fragment.currentContext!! ,this)
    }

    /**
     * Till the time new data arises, display data that is displayed in the cache
     */
    fun loadDataFromCache()
    {
        repository!!.getCachedData(fragment.currentContext!! , this)
    }

    /**Once data has been added to room this method updates the live data variable so that if there are any changes, they can be reflected*/
    fun onStallDataAddedToLocalDatabase(list : List<Stall>)
    {
        Log.d("Test" , "onStallDataAdded called $list")
        stallList.asMut().value = list
    }

    /**Runs a Query on the room database to fetch the menu of the corresponding stall is*/
    fun getMenuListForStall(stall_id : Int) : List<StallItem>
    {
        return repository!!.getMenu(stall_id)
    }

}