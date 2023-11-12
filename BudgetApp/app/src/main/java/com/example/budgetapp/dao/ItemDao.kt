package com.example.budgetapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.budgetapp.data.ItemEntity
import java.sql.RowId

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

    @Query("SELECT currency, SUM(priceOfProduct) as totalPrice FROM ITEM_ENTITY WHERE dateId=:dateId GROUP BY currency")
    suspend fun totalPrice(dateId: Int): List<TotalPriceResult>

    @Query("SELECT currency, SUM(priceOfProduct) as totalPrice FROM ITEM_ENTITY WHERE dateId BETWEEN :firstDateId AND :secondDateId GROUP BY currency")
    suspend fun totalPriceOfAllSelectedDate(firstDateId: Int, secondDateId: Int): List<TotalPriceResult>





}