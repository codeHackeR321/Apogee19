package com.anenigmatic.apogee19.screens.menu.data.room


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "stall_items")
data class StallItem (

    @PrimaryKey @SerializedName("id")var itemId: Int,
    @ColumnInfo(name = "stall_id") @SerializedName("vendor_id") var stallId: Int,
    @ColumnInfo(name = "name") @SerializedName("name") var name: String?,
    @ColumnInfo(name = "price") @SerializedName("price") var price: Int,
    @ColumnInfo(name = "is_available") @SerializedName("is_available") var isAvailable: Boolean

)