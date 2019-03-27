package com.anenigmatic.apogee19.screens.menu.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.anenigmatic.apogee19.screens.menu.data.retrofit.KindStoreItem


@Dao
interface KindStoreItemDao {
    @Query("SELECT * FROM kind_store_items")
    fun getAll(): LiveData<List<KIndStoreItemData>>

    @Insert
    fun insertAll(kindStoreItems: List<KIndStoreItemData>)

    @Query("DELETE FROM kind_store_items")
    fun deleteAll()
}