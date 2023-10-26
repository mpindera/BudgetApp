package com.example.budgetapp.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "item_entity")
data class ItemEntity(
    @PrimaryKey(autoGenerate = false)
    val itemId: Int = 0,
    val itemName: String,
    val priceOfProduct: Double,
    val dateId: Int
)
