package com.anenigmatic.apogee19.screens.menu.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Stall::class,StallItem::class,CartItem::class), version = 1)
abstract class StallsAndMenuDataBase : RoomDatabase() {
    abstract fun stallDao(): StallDao

    abstract fun stallItemDao(): StallItemDao

    abstract fun cartItemDao(): CartItemDao
}