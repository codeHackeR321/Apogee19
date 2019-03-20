package com.anenigmatic.apogee19.screens.menu.view

import android.animation.ObjectAnimator
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



///import kotlinx.android.synthetic.main.try_menu_layout.*

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
                adapter=StallListAdapter(updatedList!! , this@StallListFragment)
                layoutManager=LinearLayoutManager(currentContext)
            }
            recyViewMenu.adapter!!.notifyDataSetChanged()
        }

        model.getStallListFromServer()
        model.stallList.observe(this , stallObserver)

    }

    /**
     * This function is called when the user clicks on one of the stalls in the list
     */

    fun onStallSelected(stall_id : Int)
    {
        /**Code to start animation will come here*/
        /*recyViewMenu.visibility = View.INVISIBLE
        recyViewMenuItems.visibility = View.VISIBLE*/
        startAnimationForRecyclerView()
        recyViewMenuItems.apply {
            adapter=MenuListAdapter(model!!.getMenuListForStall(stall_id))
            layoutManager=LinearLayoutManager(currentContext)
        }
        recyViewMenuItems.adapter!!.notifyDataSetChanged()
    }

    /**
     * This method is called to start the animation between recycler views when a stall is clicked
     */

    fun startAnimationForRecyclerView()
    {
        ObjectAnimator.ofFloat(recyViewMenu , "translationX" , -backgroundPatternIMG.width.toFloat()).apply {
            duration = 1000
            start()
        }

        recyViewMenuItems.x = backgroundPatternIMG.width.toFloat()
        recyViewMenuItems.visibility = View.VISIBLE
        ObjectAnimator.ofFloat(recyViewMenuItems , "translationX" , (backgroundPatternIMG.width.toFloat() - recyViewMenuItems.width.toFloat())/16).apply {
            duration = 1000
            start()
        }
    }

}