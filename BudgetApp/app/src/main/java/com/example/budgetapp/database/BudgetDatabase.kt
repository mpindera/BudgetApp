package com.example.budgetapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.budgetapp.dao.DateDao
import com.example.budgetapp.dao.ItemDao
import com.example.budgetapp.data.DateEntity
import com.example.budgetapp.data.ItemEntity

@Database(entities = [ItemEntity::class,DateEntity::class], version = 14)
abstract class BudgetDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun dateDao(): DateDao
    companion object {
        private var INSTANCE: BudgetDatabase? = null

        fun getInstance(context: Context): BudgetDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BudgetDatabase::class.java, "budget-database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}