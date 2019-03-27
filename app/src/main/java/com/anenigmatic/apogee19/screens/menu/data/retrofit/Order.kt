package com.anenigmatic.apogee19.screens.menu.data.retrofit

import com.anenigmatic.apogee19.screens.menu.data.room.OrderItem
import com.google.gson.annotations.SerializedName

data class Order(

    @SerializedName("id") var orderId: Int,
    @SerializedName("vendor") var vendor: Vendor,
    @SerializedName("price") var price: Int,
    @SerializedName("otp") var otp: Int,
    @SerializedName("status") var status: String,
    var showotp: Boolean = false,
    @SerializedName("items") var menu: List<ResponseOrderItem>
)