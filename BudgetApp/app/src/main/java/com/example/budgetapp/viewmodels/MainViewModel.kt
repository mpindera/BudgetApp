package com.example.budgetapp.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.budgetapp.SelectionOfPages
import com.example.budgetapp.dao.TotalPriceResult
import com.example.budgetapp.data.DateEntity
import com.example.budgetapp.data.ItemEntity
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.impl.DateImpl
import com.example.budgetapp.impl.ItemImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(app: Application) : AndroidViewModel(app) {

    var selection by mutableStateOf(SelectionOfPages.HOME)

    fun updateSelection(newSelection: SelectionOfPages) {
        selection = newSelection
    }

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

    /** Item **/
    fun addItem(item: ItemEntity) {
        viewModelScope.launch {
            itemImplementation.insertItem(item)
        }
    }

    fun deleteItem(itemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            itemImplementation.deleteItem(itemId = itemId)
        }
    }

    fun dropItemDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            itemImplementation.dropDatabaseItem()
        }
    }

    fun updateItem(item: ItemEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            itemImplementation.updateItem(item)
        }
    }

    suspend fun totalPrice(dateId: Int): List<TotalPriceResult> {
        return itemImplementation.totalPrice(dateId)
    }

    suspend fun totalPriceOfAllSelectedDate(
        firstDateId: Int,
        secondDateId: Int
    ): List<TotalPriceResult> {
        return itemImplementation.totalPriceOfAllSelectedDate(
            firstDateId = firstDateId,
            secondDateId = secondDateId
        )
    }

    /** Date **/
    fun addDate(date: DateEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dateImplementation.insertDate(date = date)
        }
    }
    fun deleteDate(dateId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dateImplementation.deleteDate(dateId = dateId)
        }
    }

    fun getItemsCountForDate(dateId: Int): Int {
        return runBlocking {
            dateImplementation.getItemsCountForDate(dateId)
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
