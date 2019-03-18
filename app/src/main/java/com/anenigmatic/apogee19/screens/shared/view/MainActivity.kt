package com.anenigmatic.apogee19.screens.shared.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.anenigmatic.apogee19.R
import com.anenigmatic.apogee19.screens.events.view.EventListFragment
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import kotlinx.android.synthetic.main.act_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.rootPOV, EventListFragment())
                .commitNow()
        }

        setupBottomNavigation()
    }


    private fun setupBottomNavigation() {
        val navigationAdapter = AHBottomNavigationAdapter(this, R.menu.mn_bottom_nav)
        navigationAdapter.setupWithBottomNavigation(bottomNavAHB)

        bottomNavAHB.defaultBackgroundColor = ContextCompat.getColor(this, R.color.vio08)

        bottomNavAHB.accentColor = ContextCompat.getColor(this, R.color.vio07)
        bottomNavAHB.inactiveColor = ContextCompat.getColor(this, R.color.pnk01)

        bottomNavAHB.titleState = AHBottomNavigation.TitleState.ALWAYS_HIDE

        bottomNavAHB.setCurrentItem(3, false)

        bottomNavAHB.setOnTabSelectedListener { position, wasSelected ->

            // This removes all the Fragments except the EventListFragment(which is the root)
            supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

            if(!wasSelected) {
                when(position) {
                    0 -> {
                        Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        Toast.makeText(this, "Orders", Toast.LENGTH_SHORT).show()
                    }
                    3 -> {
                        // Since events screen is the root, we don't need to replace it here.
                    }
                    4 -> {
                        Toast.makeText(this, "More", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            true
        }
    }
}
