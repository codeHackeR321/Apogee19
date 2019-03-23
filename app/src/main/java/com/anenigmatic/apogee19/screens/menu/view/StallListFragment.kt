package com.anenigmatic.apogee19.screens.menu.view

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.PorterDuff
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
import com.anenigmatic.apogee19.StateClass
import com.anenigmatic.apogee19.screens.menu.core.StallsViewModel
import com.anenigmatic.apogee19.screens.menu.data.room.Stall
import com.anenigmatic.apogee19.screens.menu.data.room.StallItem
import kotlinx.android.synthetic.main.fra_stall_list.*
import kotlinx.android.synthetic.main.row_quantity_select.*

class StallListFragment : Fragment() {

    var currentContext : Context? = null
    lateinit var model : StallsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.fra_stall_list, container, false)


        currentContext = view.context

        return view
    }

    override fun onStart() {
        super.onStart()

        model = StallsViewModel(this)
        model.loadDataFromCache()
       // buttonCart.setColorFilter(view!!.resources.getColor(R.color.vio01),PorterDuff.Mode.SRC_IN)
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

        buttonCart.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                CartDialog().show(childFragmentManager , "CartDialog")
            }
        })

        backButton.setOnClickListener {
            startAnimationForRecyclerView(true)
        }

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
            adapter=MenuListAdapter(model.getMenuListForStall(stall_id) , this@StallListFragment)
            layoutManager=LinearLayoutManager(currentContext)
        }
        recyViewMenuItems.adapter!!.notifyDataSetChanged()
    }

    /**
     * This method is called to start the animation between recycler views when a stall is clicked
     */

    fun startAnimationForRecyclerView(isReverse : Boolean)
    {
        StateClass.state = 1
        if (!isReverse)
        {

            linearViewMenuItems.x = backgroundPatternIMG.width.toFloat()
            linearViewMenuItems.visibility = View.VISIBLE
            recyViewMenuItems.visibility=View.VISIBLE

            ObjectAnimator.ofFloat(recyViewMenu , "translationX" , -backgroundPatternIMG.width.toFloat()).apply {
                duration = 500
                start()
            }

            ObjectAnimator.ofFloat(linearViewMenuItems, "translationX" , (backgroundPatternIMG.width - linearViewMenuItems.width).toFloat()/128).apply {
                duration = 500
                start()
            }
            StateClass.state = 2

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
            StateClass.state = 0
        }

    }

    override fun onDestroyOptionsMenu() {
        Toast.makeText(currentContext , "Entered backstack" , Toast.LENGTH_LONG).show()
        if (recyViewMenuItems.isVisible)
            startAnimationForRecyclerView(true)
        else
            activity!!.onBackPressed()
    }

    fun addItemToCart(item : StallItem)
    {
        var alertBox = AlertDialog.Builder(currentContext).setPositiveButton("Add" , DialogInterface.OnClickListener { dialog, which ->
            Toast.makeText(currentContext , "Hello" , Toast.LENGTH_LONG).show()
            })
            .setNegativeButton("Cancel" , DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(currentContext , "Bye" , Toast.LENGTH_LONG).show()
            })
            .setTitle(item.name)
            .setView(R.layout.row_quantity_select)
            .create()
        alertBox.show()
        alertBox.AddButton.setOnClickListener {
            alertBox.TextQuantity.text  = (Integer.parseInt(alertBox.TextQuantity.text.toString()) + 1).toString()
        }

        alertBox.SubtractButton.setOnClickListener {
            if (Integer.parseInt(alertBox.TextQuantity.text.toString()) > 1)
            {
                alertBox.TextQuantity.text  = (Integer.parseInt(alertBox.TextQuantity.text.toString()) - 1).toString()
            }
            else
            {
                Toast.makeText(currentContext , "Atleast One item must be selected" , Toast.LENGTH_LONG).show()
            }
        }

    }

}