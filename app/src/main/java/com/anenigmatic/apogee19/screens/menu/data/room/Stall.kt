package com.anenigmatic.apogee19.screens.menu.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "stall")
data class Stall (

    @PrimaryKey @SerializedName("id")var stallId: Int,
    @ColumnInfo(name = "name") @SerializedName("name")var name: String?,
    @ColumnInfo(name = "description") @SerializedName("description") var description: String?,
    @ColumnInfo(name = "closed") @SerializedName("closed")var isClosed: Boolean
    //@Ignore @SerializedName("menu") var menu: List<StallItem>

)