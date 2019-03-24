package com.anenigmatic.apogee19.screens.menu.data.retrofit

import com.google.gson.annotations.SerializedName

data class OrderComfirmation(
    @SerializedName("display_message") val message: String
)