package com.anenigmatic.apogee19.screens.menu.data.retrofit

import com.squareup.moshi.Json

data class KindStoreItem(
    @field:Json(name = "price") val price: Int,
    @field:Json(name = "image") val image: String? = "",
    @field:Json(name = "is_available") val isAvailable: Boolean
)