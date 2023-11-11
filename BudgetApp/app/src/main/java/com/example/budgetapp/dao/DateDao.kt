package com.example.budgetapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.budgetapp.data.DateEntity
import com.example.budgetapp.relactions.DateWithItems

@Dao
interface DateDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDate(date: DateEntity)

    @Query("SELECT * FROM DATE_ENTITY")
    fun getAllDate(): LiveData<List<DateEntity>>

    @Transaction
    @Query("SELECT * FROM DATE_ENTITY WHERE dateId = :dateId")
    suspend fun getDateWithItems(dateId: Int): List<DateWithItems>

    @Transaction
    @Query("SELECT EXISTS(SELECT * FROM DATE_ENTITY WHERE day = :day AND month = :month AND year = :year)")
    suspend fun checkIfDateExists(day: String, month: String, year: String): Boolean

    @Query("DELETE FROM date_entity WHERE dateId=:dateId")
    suspend fun deleteDate(dateId: Int)

    @Query("DELETE FROM DATE_ENTITY")
    suspend fun dropDatabaseDate()


    @Transaction
    @Query("SELECT COUNT(*) FROM ITEM_ENTITY WHERE dateId = :dateId")
    suspend fun getItemsCountForDate(dateId: Int): Int
}