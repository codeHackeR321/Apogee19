package com.anenigmatic.apogee19.screens.menu.data.retrofit

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class OrderShell(
    @SerializedName("id") var shellId: Int,
    @SerializedName("orders") var order: List<Order>,
    @SerializedName("timestamp") var timestamp: String
)