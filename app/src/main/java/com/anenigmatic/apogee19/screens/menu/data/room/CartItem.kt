package com.anenigmatic.apogee19.screens.menu.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cart_items")
data class CartItem(

    @PrimaryKey @SerializedName("item_id")var itemId: Int,
    @ColumnInfo(name = "stall_id") var stallId: Int,
    @ColumnInfo(name = "name") @SerializedName("name") var name: String?,
    @ColumnInfo(name = "price") @SerializedName("price") var price: Int,
    @ColumnInfo(name = "quantity") @SerializedName("quantity") var quantity: Int

)