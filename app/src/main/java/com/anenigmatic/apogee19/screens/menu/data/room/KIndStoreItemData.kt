package com.anenigmatic.apogee19.screens.menu.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kind_store_items")
data class KIndStoreItemData (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val price: Int,
    val image: String?,
    @ColumnInfo(name = "is_available") val isAvailable: Boolean
)