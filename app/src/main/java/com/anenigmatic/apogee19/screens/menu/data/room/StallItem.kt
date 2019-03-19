package com.anenigmatic.apogee19.screens.menu.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stall_items")
data class StallItem (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "menu_id") var menu_id : Int,
    @ColumnInfo(name = "stall_id") var stall_id: Int,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "price") var price: Int

)