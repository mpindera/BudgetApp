package com.example.budgetapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "date_entity")
data class DateEntity(

    @PrimaryKey(autoGenerate = true)
    val dateId: Int = 0,
    val day: String,
    val month: String,
    val year: String
)