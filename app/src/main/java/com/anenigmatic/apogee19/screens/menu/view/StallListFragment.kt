package com.anenigmatic.apogee19.screens.menu.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anenigmatic.apogee19.R
import com.anenigmatic.apogee19.screens.menu.core.StallsViewModel
import com.anenigmatic.apogee19.screens.menu.data.room.Stall
import kotlinx.android.synthetic.main.fra_menu_list.*

class StallListFragment : Fragment() {

    var currentContext : Context? = null
    lateinit var model : StallsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.fra_menu_list, container, false)
        currentContext = view.context

        return view
    }

    override fun onStart() {
        super.onStart()

        model = StallsViewModel(this)
        model.loadDataFromCache()

        val stallObserver = Observer<List<Stall>>
        {updatedList ->
            Log.d("Test" , "Obsreved correctly $updatedList")
            recyViewMenu.apply {
                adapter=StallListAdapter(updatedList!!)
                layoutManager=LinearLayoutManager(currentContext)
            }
            recyViewMenu.adapter!!.notifyDataSetChanged()
        }

        model.getStallListFromServer()
        model.stallList.observe(this , stallObserver)
        
    }
}