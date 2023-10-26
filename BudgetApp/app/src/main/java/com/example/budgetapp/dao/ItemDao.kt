package com.example.budgetapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.budgetapp.data.ItemEntity

@Dao
interface ItemDao {
    @Insert
    suspend fun insertItem(item: ItemEntity)

    @Query("SELECT * FROM ITEM_ENTITY")
    fun getAllItem(): LiveData<List<ItemEntity>>

    @Delete
    suspend fun deleteItem(item: ItemEntity)

    @Query("DELETE FROM ITEM_ENTITY")
    suspend fun dropDatabaseItem()

}