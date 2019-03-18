package com.anenigmatic.apogee19.screens.shared.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anenigmatic.apogee19.R
import com.anenigmatic.apogee19.screens.events.view.EventListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.rootPOV, EventListFragment())
                .commitNow()
        }
    }
}
