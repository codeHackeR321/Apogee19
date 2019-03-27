package com.anenigmatic.apogee19.screens.orderHistory.core

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anenigmatic.apogee19.ApogeeApp
import com.anenigmatic.apogee19.screens.menu.data.room.OrderItem
import com.anenigmatic.apogee19.screens.menu.data.room.Stall
import com.anenigmatic.apogee19.screens.orderHistory.view.OrderHistory
import com.example.manish.apogeewallet.screens.menu.data.room.PastOrder

class OrderHistoryViewModel(fragmentPassed: OrderHistory): ViewModel(){

    private var repository = ApogeeApp.menuRepository
    var orderList: LiveData<List<PastOrder>> = MutableLiveData()
    var orderItemList: LiveData<List<OrderItem>> = MediatorLiveData()

    fun getOrderListFromServer(){

        repository.refreshPastOrders()
        orderList = repository.getOrders()

    }

    fun getOrderListForOrder(orderId: Int){

        orderItemList = repository.getOrderItems(orderId)

    }

    fun getStallListFromServer(): LiveData<List<Stall>>
    {
        return repository.getStalls()
    }

    fun onOTPClicked(orderId: Int){

        repository.changeOrderOtpStatus(orderId)

    }
}
