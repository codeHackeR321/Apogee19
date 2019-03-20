package com.anenigmatic.apogee19.screens.menu.view

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.anenigmatic.apogee19.R
import com.jakewharton.rxbinding3.view.layoutChanges
import kotlinx.android.synthetic.main.fra_menu_list.*
import java.util.ArrayList

class MenuListFragment : Fragment() {

    var currentContext : Context? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.fra_menu_list, container, false)
        currentContext = view.context

        return view
    }

    override fun onStart() {
        super.onStart()

        var mydataset= ArrayList<String>()
        mydataset.add("Jassi De ParontheJassi De Paronthe")
        mydataset.add("Jassi De Paronthe")
        mydataset.add("Jassi De Paronthe")
        mydataset.add("Jassi De Paronthe")
        mydataset.add("Jassi De Paronthe")
        mydataset.add("Jassi De Paronthe")
        mydataset.add("Jassi De Paronthe")
        mydataset.add("Jassi De Paronthe")
        mydataset.add("Jassi De Paronthe")
        mydataset.add("Jassi De Paronthe")
        mydataset.add("Jassi De Paronthe")
        mydataset.add("Jassi De Paronthe")
        var customadapter1 = MenuListAdapter(mydataset)
        recyViewMenu.apply {
            adapter=customadapter1
            layoutManager=LinearLayoutManager(currentContext)
        }
        customadapter1.notifyDataSetChanged()


        var mydataset2= ArrayList<String>()
        mydataset2.add("Jassi De ParontheJassi De Paronthe")
        mydataset2.add("Jassi ")
        mydataset2.add("Jassi ")
        mydataset2.add("Jassi ")
        mydataset2.add("Jassi ")
        mydataset2.add("Jassi ")
        mydataset2.add("Jassi ")
        mydataset2.add("Jassi ")
        mydataset2.add("Jassi ")
        mydataset2.add("Jassi ")
     var customadapter2= MenuListAdapter(mydataset2)
        recyViewMenuItems.apply {
            adapter=customadapter2
            layoutManager=LinearLayoutManager(currentContext)
        }
        customadapter2.notifyDataSetChanged()

 buttonCart.setOnClickListener(object : View.OnClickListener{
     override fun onClick(v: View?) {
         ObjectAnimator.ofFloat(recyViewMenu, "translationX", -backgroundPatternIMG.width.toFloat()).apply {
             duration = 1000
             start()
         }



         view1.x=backgroundPatternIMG.width.toFloat()
         view1.visibility=View.VISIBLE
         ObjectAnimator.ofFloat(view1, "translationX", (backgroundPatternIMG.width.toFloat()-view1.width.toFloat())/16).apply {
             duration = 1000
             start()
         }

      }
 })





    }
}