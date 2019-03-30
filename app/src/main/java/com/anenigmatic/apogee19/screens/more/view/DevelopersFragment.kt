package com.anenigmatic.apogee19.screens.more.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.anenigmatic.apogee19.R
import kotlinx.android.synthetic.main.fra_developers.view.*

class DevelopersFragment: Fragment(){


    private var currentContext: Context? = null

    var developerData : ArrayList<Triple<String,String,String>> = arrayListOf(
        Triple("Nishant Mahajan","App Developer",""),
        Triple("Manish K Thakur","App Developer",""),
        Triple("Hemanth V Alluri","Backend Developer",""),
        Triple("Divyam Goel","Backend Developer",""),
        Triple("Raghav Arora","Backend Developer",""),
        Triple("Yash Bhagat","UX/UI Designer",""),
        Triple("Prarabdh Garg","App Developer",""),
        Triple("Suyash Soni","App Developer",""),
        Triple("Akshat Gupta","App Developer",""),
        Triple("Ishita Aggarwal","App Developer","")
    )


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fra_developers, container, false)
        currentContext = context

        return view
    }

    override fun onStart() {
        super.onStart()

        view!!.developersRCY.layoutManager = LinearLayoutManager(context)
        view!!.developersRCY.adapter = DevelopersAdapter(developerData, currentContext!!)

    }
}