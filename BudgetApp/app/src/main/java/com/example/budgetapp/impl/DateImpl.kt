package com.example.budgetapp.impl

import androidx.lifecycle.LiveData
import com.example.budgetapp.dao.DateDao
import com.example.budgetapp.data.DateEntity
import com.example.budgetapp.relactions.DateWithItems
import kotlinx.coroutines.runBlocking


class DateImpl(private val budgetDatabase: DateDao) : DateDao {
    val readAllDataFormDate: LiveData<List<DateEntity>> = budgetDatabase.getAllDate()

    override suspend fun insertDate(date: DateEntity) {
        budgetDatabase.insertDate(date = date)
    }

    override fun getAllDate(): LiveData<List<DateEntity>> {
        return budgetDatabase.getAllDate()
    }

    override suspend fun getDateWithItems(dateId: Int): List<DateWithItems> {
        return budgetDatabase.getDateWithItems(dateId = dateId)
    }

    override suspend fun checkIfDateExists(day: String, month: String, year: String): Boolean {
        return budgetDatabase.checkIfDateExists(day = day, month = month, year = year)
    }

    override suspend fun deleteDate(dateId: Int) {
        budgetDatabase.deleteDate(dateId = dateId)
    }

    override suspend fun dropDatabaseDate() {
        budgetDatabase.dropDatabaseDate()
    }

    override suspend fun getItemsCountForDate(dateId: Int): Int {
        return runBlocking {
            budgetDatabase.getItemsCountForDate(dateId)
        }
    }
}