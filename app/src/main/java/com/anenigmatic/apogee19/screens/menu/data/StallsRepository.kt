package com.anenigmatic.apogee19.screens.menu.data

import android.content.Context
import com.anenigmatic.apogee19.screens.menu.core.StallsViewModel
import com.anenigmatic.apogee19.screens.menu.data.room.Stall
import com.anenigmatic.apogee19.screens.menu.data.room.StallItem

interface StallsRepository {

    fun getStalls(context: Context , viewModel: StallsViewModel)

    fun getMenu(stall_id : Int) : List<StallItem>

    fun addItemToCart()

    fun getCart()

    fun placeOrder()

    fun getCachedData(context : Context , viewModel: StallsViewModel)

}