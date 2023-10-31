package com.example.budgetapp.impl

import androidx.lifecycle.LiveData
import com.example.budgetapp.dao.DateDao
import com.example.budgetapp.data.DateEntity
import com.example.budgetapp.relactions.DateWithItems


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

    override suspend fun checkIfDateExists(day: Int, month: String, year: Int): DateEntity? {
        return budgetDatabase.checkIfDateExists(day = day, month = month, year = year)
    }

    override suspend fun deleteDate(date: DateEntity) {
        budgetDatabase.deleteDate(date = date)
    }

    override suspend fun dropDatabaseDate() {
        budgetDatabase.dropDatabaseDate()
    }
}