package com.anenigmatic.apogee19.screens.more.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anenigmatic.apogee19.R
import kotlinx.android.synthetic.main.fra_epc_blog.view.*

class EpcBlogFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootPOV = inflater.inflate(R.layout.fra_epc_blog, container, false)

        rootPOV.epcBlogWBV.loadUrl("https://epcbits.wordpress.com/")

        return rootPOV
    }
}