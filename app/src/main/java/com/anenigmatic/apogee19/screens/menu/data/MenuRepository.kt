package com.anenigmatic.apogee19.screens.menu.data

import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LiveData
import com.anenigmatic.apogee19.screens.menu.data.room.CartItem
import com.anenigmatic.apogee19.screens.menu.data.room.Stall
import com.anenigmatic.apogee19.screens.menu.data.room.StallItem

interface MenuRepository {

    fun getStalls(): LiveData<List<Stall>>

    fun getMenu(stall: Stall) : LiveData<List<StallItem>>

    fun getMenu(stallId: Int) : LiveData<List<StallItem>>

    fun refreshStallAndMenu()

    fun addItemToCart(stallItem: StallItem, quantity: Int)

    fun addItemToCart(newCartItem: CartItem)

    fun getCartItems(): LiveData<List<CartItem>>

    fun placeOrder()

    fun deleteCartItem(item : CartItem)

}