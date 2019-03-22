package com.anenigmatic.apogee19.screens.menu.view

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anenigmatic.apogee19.R
import com.anenigmatic.apogee19.screens.events.view.FilterDialog
import com.anenigmatic.apogee19.screens.menu.core.StallsViewModel
import com.anenigmatic.apogee19.screens.menu.data.room.Stall
import com.anenigmatic.apogee19.screens.menu.data.room.StallItem
import kotlinx.android.synthetic.main.act_main.*

import kotlinx.android.synthetic.main.try_menu_layout.*


///import kotlinx.android.synthetic.main.try_menu_layout.*

class StallListFragment : Fragment() {

    var currentContext : Context? = null
    lateinit var model : StallsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.try_menu_layout, container, false)
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

        // to check the cart layout

        var trialList= ArrayList<String>()
        trialList.add("Curry of life")
        trialList.add("Double chicken roll")
        trialList.add("Double chicken roll with")
        trialList.add("Double chicken roll with extra ")
        trialList.add("Double chicken roll cheese")
        trialList.add("Double chicken roll with")
        trialList.add("Double chicken roll with extra ")
        trialList.add("Double chicken roll cheese")
        var cartAdapter= CartAdapter(trialList)
        recyViewCart.apply {
            adapter= cartAdapter
            layoutManager=LinearLayoutManager(currentContext)
        }

        buttonCart.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                CartDialog().show(childFragmentManager, "CartDialog")
                /*if(cardViewCart.visibility==View.VISIBLE)
                    cardViewCart.visibility=View.INVISIBLE
                else
                {
                    cardViewCart.visibility=View.VISIBLE
                    cartAdapter.notifyDataSetChanged()
                }*/
            }
        })



    }

    /**
     * This function is called when the user clicks on one of the stalls in the list
     */

    fun onStallSelected(stall_id : Int)
    {
        /**Code to start animation will come here*/
        /*recyViewMenu.visibility = View.INVISIBLE
        recyViewMenuItems.visibility = View.VISIBLE*/
        startAnimationForRecyclerView(false)
        recyViewMenuItems.apply {
            adapter=MenuListAdapter(model.getMenuListForStall(stall_id))
            layoutManager=LinearLayoutManager(currentContext)
        }
        recyViewMenuItems.adapter!!.notifyDataSetChanged()
    }

    /**
     * This method is called to start the animation between recycler views when a stall is clicked
     */

    fun startAnimationForRecyclerView(isReverse : Boolean)
    {

        if (!isReverse)
        {
            ObjectAnimator.ofFloat(recyViewMenu , "translationX" , -backgroundPatternIMG.width.toFloat()).apply {
                duration = 500
                start()
            }

            linearViewMenuItems.x = backgroundPatternIMG.width.toFloat()
            linearViewMenuItems.visibility = View.VISIBLE
            recyViewMenuItems.visibility=View.VISIBLE

            ObjectAnimator.ofFloat(linearViewMenuItems, "translationX" , (backgroundPatternIMG.width - linearViewMenuItems.width).toFloat()/128).apply {
                duration = 500
                start()
            }

        }
        else
        {
            ObjectAnimator.ofFloat(linearViewMenuItems , "translationX" , backgroundPatternIMG.width.toFloat()).apply {
                duration = 500
                start()
            }

            recyViewMenu.visibility = View.VISIBLE

            ObjectAnimator.ofFloat(recyViewMenu, "translationX" , (backgroundPatternIMG.width - recyViewMenu.width).toFloat()/128).apply {
                duration = 500
                start()
            }

        }

    }

    override fun onDestroyOptionsMenu() {
        Toast.makeText(currentContext , "Entered backstack" , Toast.LENGTH_LONG).show()
        if (recyViewMenuItems.isVisible)
            startAnimationForRecyclerView(true)
        else
            activity!!.onBackPressed()
    }

}