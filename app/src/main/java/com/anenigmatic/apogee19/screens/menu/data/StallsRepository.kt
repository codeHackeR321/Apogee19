package com.anenigmatic.apogee19.screens.menu.data

import android.content.Context
import com.anenigmatic.apogee19.screens.menu.core.StallsViewModel
import com.anenigmatic.apogee19.screens.menu.data.room.Stall

interface StallsRepository {

    fun getStalls(context: Context , viewModel: StallsViewModel)

    fun getMenu(stall: Stall)

    fun addItemToCart()

    fun getCart()

    fun placeOrder()

    fun getCachedData(context : Context , viewModel: StallsViewModel)

}