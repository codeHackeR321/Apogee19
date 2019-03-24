package com.example.manish.apogeewallet.screens.menu.data.retrofit

import androidx.room.ColumnInfo
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.anenigmatic.apogee19.screens.menu.data.room.StallItem
import com.google.gson.annotations.SerializedName

data class StallAndMenu(
    @SerializedName("id")var stallId: Int,
    @SerializedName("name")var name: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("closed")var isClosed: Boolean,
    @SerializedName("menu") var menu: List<StallItem>
)