package com.ceresdroidxapps.taskapp.data.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update


interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(vararg  item: T)

    @Update
    suspend fun updateItem(item: T)

    @Delete
    suspend fun deleteItem(item: T)

}