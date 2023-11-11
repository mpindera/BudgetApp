package com.example.budgetapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.budgetapp.data.ItemEntity

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(item: ItemEntity)

    @Query("SELECT * FROM ITEM_ENTITY")
    fun getAllItem(): LiveData<List<ItemEntity>>

    @Query("DELETE FROM ITEM_ENTITY WHERE itemId=:itemId")
    suspend fun deleteItem(itemId: Int)

    @Query("DELETE FROM ITEM_ENTITY")
    suspend fun dropDatabaseItem()

    @Update
    suspend fun updateItem(item: ItemEntity)
}