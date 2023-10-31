package com.example.budgetapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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

    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(Color(0xFFFD418D), Color(0xFF818181)),
    )
    val colorSpacer = listOf(
        Color.Gray,
        Color.Green,
        Color.Red,
        Color.Black,
        Color.Blue,
        Color.Cyan,
        Color.Magenta,
        Color.Yellow,
    )

}