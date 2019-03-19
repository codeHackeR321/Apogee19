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
        var customadapter = MenuListAdapter(mydataset)
        recyViewMenu.apply {
            adapter=customadapter
            layoutManager=LinearLayoutManager(currentContext)
        }
        customadapter.notifyDataSetChanged()


        mydataset.clear()
        mydataset.add("Jassi De ParontheJassi De Paronthe")
        mydataset.add("Jassi ")
        mydataset.add("Jassi ")
        mydataset.add("Jassi ")
        mydataset.add("Jassi ")
        mydataset.add("Jassi ")
        mydataset.add("Jassi ")
        mydataset.add("Jassi ")
        mydataset.add("Jassi ")
        mydataset.add("Jassi ")

        recyViewMenuItems.apply {
            adapter=customadapter
            layoutManager=LinearLayoutManager(currentContext)
        }
        customadapter.notifyDataSetChanged()

 buttonCart.setOnClickListener(object : View.OnClickListener{
     override fun onClick(v: View?) {
         ObjectAnimator.ofFloat(recyViewMenu, "translationX", -recyViewMenuItems.width.toFloat()).apply {
             duration = 1000
             start()
         }



         recyViewMenuItems.x=backgroundPatternIMG.width.toFloat()
         recyViewMenuItems.visibility=View.VISIBLE
         ObjectAnimator.ofFloat(recyViewMenuItems, "translationX", (backgroundPatternIMG.width.toFloat()-recyViewMenuItems.width.toFloat())/4).apply {
             duration = 1000
             start()
         }

        /* recyViewMenuItems.animate()
             .translationXBy(-(recyViewMenuItems.width.toFloat()+(backgroundPatternIMG.width.toFloat()-recyViewMenuItems.width.toFloat())/2)).setDuration(1000)

*/
         /*recyViewMenu.animate().translationXBy(-backgroundPatternIMG.width.toFloat()).setDuration(1000).setListener(object : Animator.AnimatorListener
         {
             override fun onAnimationRepeat(animation: Animator?) {

             }

             override fun onAnimationEnd(animation: Animator?) {
                 recyViewMenu.visibility=View.GONE
             }

             override fun onAnimationCancel(animation: Animator?) {
             }

             override fun onAnimationStart(animation: Animator?) {
             }

         }
         )
         mydataset.clear()
         mydataset.add("Jassi De ParontheJassi De Paronthe")
         mydataset.add("Jassi ")
         mydataset.add("Jassi ")
         mydataset.add("Jassi ")
         mydataset.add("Jassi ")
         mydataset.add("Jassi ")
         mydataset.add("Jassi ")
         mydataset.add("Jassi ")
         mydataset.add("Jassi ")
         mydataset.add("Jassi ")

         recyViewMenuItems.apply {
             adapter=customadapter
             layoutManager=LinearLayoutManager(currentContext)
         }
         customadapter.notifyDataSetChanged()
         recyViewMenuItems.x=backgroundPatternIMG.width.toFloat()
         recyViewMenuItems.visibility=View.VISIBLE
         recyViewMenuItems.animate()
             .translationXBy(-(recyViewMenuItems.width.toFloat()+(backgroundPatternIMG.width.toFloat()-recyViewMenuItems.width.toFloat())/2)).setDuration(1000)
     */}
 })





    }
}