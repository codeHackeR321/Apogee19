package com.anenigmatic.apogee19.screens.menu.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StallsDao {
    @Query("SELECT * FROM stall")
    fun getAll(): List<Stall>

    @Insert
    fun insertAll(stalls: List<Stall>)

    @Delete
    fun delete(stall: Stall)

    @Query("DELETE FROM stall")
    fun deleteAll()
}