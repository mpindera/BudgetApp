package com.example.budgetapp.relactions

import androidx.room.Embedded
import androidx.room.Relation
import com.example.budgetapp.data.DateEntity
import com.example.budgetapp.data.ItemEntity

data class DateWithItems(
    @Embedded val date: DateEntity,
    @Relation(
        parentColumn = "dateId",
        entityColumn = "dateId"
    )
    val items: List<ItemEntity>
)