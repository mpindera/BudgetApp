package com.example.budgetapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.Calendar

object StaticObjects {
    val days = (1..31).map {
        if (it < 10) {
            "0${it}"
        } else {
            it.toString()
        }
    }
    val months = listOf(
        "Styczeń",
        "Luty",
        "Marzec",
        "Kwiecień",
        "Maj",
        "Czerwiec",
        "Lipiec",
        "Sierpień",
        "Wrzesień",
        "Październik",
        "Listopad",
        "Grudzień"
    )
    val years = (2023..2100).map { it.toString() }

    val inputModifier = Modifier.border(
        BorderStroke(
            width = 2.dp,
            color = Color.Gray
        )
    )
}