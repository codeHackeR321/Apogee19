package com.anenigmatic.apogee19.screens.menu.data.retrofit

import com.google.gson.annotations.SerializedName

data class Vendor(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String
)