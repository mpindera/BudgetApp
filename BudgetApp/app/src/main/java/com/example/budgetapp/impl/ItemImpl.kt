package com.example.budgetapp.impl

import androidx.lifecycle.LiveData
import com.example.budgetapp.dao.ItemDao
import com.example.budgetapp.dao.TotalPriceResult
import com.example.budgetapp.data.ItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemImpl(private val budgetDatabase: ItemDao) : ItemDao {
    val readAllDataItem: LiveData<List<ItemEntity>> = budgetDatabase.getAllItem()

    override suspend fun insertItem(item: ItemEntity) = withContext(Dispatchers.IO) {
        budgetDatabase.insertItem(item = item)
    }

    override fun getAllItem(): LiveData<List<ItemEntity>> {
        return budgetDatabase.getAllItem()
    }

    override suspend fun deleteItem(itemId: Int) = withContext(Dispatchers.IO) {
        budgetDatabase.deleteItem(itemId = itemId)
    }

    override suspend fun dropDatabaseItem() = withContext(Dispatchers.IO) {
        budgetDatabase.dropDatabaseItem()
    }

    override suspend fun updateItem(item: ItemEntity) {
        budgetDatabase.updateItem(item = item)
    }

    override suspend fun totalPrice(dateId: Int): List<TotalPriceResult> {
        return budgetDatabase.totalPrice(dateId = dateId)
    }

    override suspend fun totalPriceOfAllSelectedDate(
        firstDateId: Int, secondDateId: Int
    ): List<TotalPriceResult> {
        return budgetDatabase.totalPriceOfAllSelectedDate(
            firstDateId = firstDateId, secondDateId = secondDateId
        )
    }


}