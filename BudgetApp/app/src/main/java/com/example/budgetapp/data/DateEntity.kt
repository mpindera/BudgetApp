package com.example.budgetapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "date_entity")
data class DateEntity(

    @PrimaryKey(autoGenerate = true)
    val dateId: Int,
    val day: Int,
    val month: String,
    val year: Int
)