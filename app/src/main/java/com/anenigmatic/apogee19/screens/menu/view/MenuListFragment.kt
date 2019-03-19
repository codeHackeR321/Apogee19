package com.anenigmatic.apogee19.screens.menu.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.anenigmatic.apogee19.R
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
        mydataset.add("Jassi De Paronthe")
        var customadapter = MenuListAdapter(mydataset)
        recyViewMenu.apply {
            adapter=customadapter
            layoutManager=LinearLayoutManager(currentContext)
        }
        customadapter.notifyDataSetChanged()

    }
}