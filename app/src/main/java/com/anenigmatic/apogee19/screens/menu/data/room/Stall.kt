package com.anenigmatic.apogee19.screens.menu.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stall")
data class Stall (

    @PrimaryKey var stall_id: Int,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "description") var description: String?,
    @ColumnInfo(name = "closed") var isClosed: Boolean

)