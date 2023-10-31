package com.example.budgetapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.budgetapp.data.DateEntity
import com.example.budgetapp.data.ItemEntity
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.impl.DateImpl
import com.example.budgetapp.impl.ItemImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {

    val myString = listOf("Day:", "Month:", "Year:", "Border color:")

    val readAllDataItemEntity: LiveData<List<ItemEntity>>
    val itemImplementation: ItemImpl

    val readAllDataDateEntity: LiveData<List<DateEntity>>
    val dateImplementation: DateImpl

    init {
        val itemDao = BudgetDatabase.getInstance(app).itemDao()
        itemImplementation = ItemImpl(itemDao)
        readAllDataItemEntity = itemImplementation.readAllDataItem

        val dateDao = BudgetDatabase.getInstance(app).dateDao()
        dateImplementation = DateImpl(dateDao)
        readAllDataDateEntity = dateImplementation.readAllDataFormDate
    }

    fun addItem(item: ItemEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            itemImplementation.insertItem(item = item)
        }
    }

    fun deleteItem(item: ItemEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            itemImplementation.deleteItem(item = item)
        }
    }

    fun dropItemDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            itemImplementation.dropDatabaseItem()
        }
    }

    ///
    fun addDate(date: DateEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dateImplementation.insertDate(date = date)
        }
    }

    fun dropDateDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            dateImplementation.dropDatabaseDate()
        }
    }

    ///
    fun showAll(dateId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dateImplementation.getDateWithItems(dateId)
        }
    }

    suspend fun checkIfDateExists(day: String, month: String, year: String): Boolean {
        return dateImplementation.checkIfDateExists(day = day, month = month, year = year)
    }


}
