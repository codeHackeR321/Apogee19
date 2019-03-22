package com.anenigmatic.apogee19.screens.shared.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.anenigmatic.apogee19.R
import com.anenigmatic.apogee19.screens.events.view.EventListFragment
import com.anenigmatic.apogee19.screens.menu.view.StallListFragment
import com.anenigmatic.apogee19.screens.more.view.MoreFragment
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import kotlinx.android.synthetic.main.act_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.navHostFRM, EventListFragment())
                .commitNow()

            setupBottomNavigation(3)
        } else {
            setupBottomNavigation(savedInstanceState.getInt("BOTTOM_NAV_CURRENT_ITEM"))
        }
    }

    override fun onSaveInstanceState(saveInstanceState: Bundle) {
        super.onSaveInstanceState(saveInstanceState)

        saveInstanceState.putInt("BOTTOM_NAV_CURRENT_ITEM", bottomNavAHB.currentItem)
    }


    private fun setupBottomNavigation(currentItem: Int) {
        val navigationAdapter = AHBottomNavigationAdapter(this, R.menu.mn_bottom_nav)
        navigationAdapter.setupWithBottomNavigation(bottomNavAHB)

        bottomNavAHB.defaultBackgroundColor = ContextCompat.getColor(this, R.color.wht01)

        bottomNavAHB.titleState = AHBottomNavigation.TitleState.ALWAYS_HIDE

        bottomNavAHB.setOnTabSelectedListener { position, wasSelected ->

            // This removes all the Fragments except the EventListFragment(which is the root)
            supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

            if(!wasSelected) {
                when(position) {
                    0 -> {
                        Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.navHostFRM,
                                StallListFragment()
                            )
                            .addToBackStack(null)
                            .commitAllowingStateLoss()
                       /* Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show()*/
                    }
                    2 -> {
                        Toast.makeText(this, "Orders", Toast.LENGTH_SHORT).show()
                    }
                    3 -> {
                        bottomNavAHB.accentColor = ContextCompat.getColor(this, R.color.vio07)
                        bottomNavAHB.inactiveColor = ContextCompat.getColor(this, R.color.pnk01)
                    }
                    4 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.navHostFRM, MoreFragment())
                            .addToBackStack(null)
                            .commitAllowingStateLoss()

                        bottomNavAHB.accentColor = ContextCompat.getColor(this, R.color.yel02)
                        bottomNavAHB.inactiveColor = ContextCompat.getColor(this, R.color.yel01)
                    }
                }
            }
            true
        }

        bottomNavAHB.setCurrentItem(currentItem, true)
    }
}
