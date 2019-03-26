package com.anenigmatic.apogee19.screens.menu.data.retrofit

import com.google.gson.annotations.SerializedName

data class ResponseOrderItem(
    @SerializedName("id") var itemId: Int,
    @SerializedName("name") var name: String,
    @SerializedName("unit_price") var price: Int,
    @SerializedName("quantity") var quantity: Int
)