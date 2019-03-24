package com.anenigmatic.apogee19.screens.menu.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anenigmatic.apogee19.screens.menu.data.MenuRepositoryImpl
import com.anenigmatic.apogee19.screens.menu.data.room.CartItem
import com.anenigmatic.apogee19.screens.menu.view.CartDialog

class CartViewModel(instance : CartDialog) : ViewModel()
{
    var cartList : LiveData<List<CartItem>> = MutableLiveData<List<CartItem>>()
    var repository : MenuRepositoryImpl? = null
    var fragment = instance

    init
    {
        /* Log.d("Test" , "Entered init")
         try {
             repository = MenuRepositoryImpl(instance.currentContext!!)
         }
         catch (e : Exception)
         {
             repository = MenuRepositoryImpl(instance.currentContext!!).getInstance()
         }*/
        repository = MenuRepositoryImpl(instance.currentContect!!)
    }

    fun getCartItems()
    {
        cartList = repository!!.getCartItems()
    }

    fun removeItemFromCart(item : CartItem)
    {
        repository!!.deleteCartItem(item)
        repository!!.getCartItems()
    }

    fun placeOrder()
    {
        repository!!.placeOrder()
    }

}