package com.example.budgetapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.budgetapp.data.DateEntity
import com.example.budgetapp.data.ItemEntity
import com.example.budgetapp.relactions.DateWithItems
import java.util.Date

@Dao
interface DateDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDate(date: DateEntity)

    @Query("SELECT * FROM DATE_ENTITY")
    fun getAllDate(): LiveData<List<DateEntity>>

    @Transaction
    @Query("SELECT * FROM DATE_ENTITY WHERE dateId = :dateId")
    suspend fun getDateWithItems(dateId: Int): List<DateWithItems>

    @Delete
    suspend fun deleteDate(date: DateEntity)

    @Query("DELETE FROM DATE_ENTITY")
    suspend fun dropDatabaseDate()

}