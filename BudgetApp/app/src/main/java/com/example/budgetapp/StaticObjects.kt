package com.example.budgetapp

import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.budgetapp.api.RetrofitInstance
import com.example.budgetapp.viewmodels.CurrencyViewModel
import com.example.budgetapp.viewmodels.MainViewModel
import java.text.DateFormatSymbols
import java.util.Calendar

object StaticObjects {

    val days = (1..31).map {
        if (it < 10) {
            "0${it}"
        } else {
            it.toString()
        }
    }
    @Composable
    fun getLocalizedMonthNames(): List<String> {
        val locale = LocalConfiguration.current.locale
        val monthSymbols = DateFormatSymbols.getInstance(locale).months

        return monthSymbols.toList().filter { it.isNotEmpty() }
    }



    val years = (2023..2100).map { it.toString() }

    val inputModifier = Modifier.border(
        BorderStroke(
            width = 2.dp,
            color = Color.Gray
        )
    )

    val colorSpacer = listOf(
        Color.Green,
        Color.Red,
        Color.Black,
        Color.Blue,
        Color.Cyan,
        Color.Magenta,
        Color.Yellow,
    )

    val apiUtil = CurrencyViewModel(api = RetrofitInstance().api)



}