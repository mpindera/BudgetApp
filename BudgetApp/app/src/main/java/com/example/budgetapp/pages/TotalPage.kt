package com.example.budgetapp.pages

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.budgetapp.TopAppBarTemplate
import com.example.budgetapp.dao.TotalPriceResult
import com.example.budgetapp.viewmodels.MainViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun TotalPage(mainViewModel: MainViewModel, navController: NavHostController, dateId: Int) {

    Scaffold(
        topBar = {
            TopAppBarTemplate(mainViewModel = mainViewModel, navigationIconClick = {
                navController.popBackStack()
            }, actionIconClick = {

            })
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
        ) {
            ShowOfTotalPrice(mainViewModel = mainViewModel, dateId = dateId)
        }
    }
}

@Composable
fun ShowOfTotalPrice(mainViewModel: MainViewModel, dateId: Int) {
    val mapOfCurrencyAndTotalPrice = mutableMapOf<String, Double>()
    runBlocking {
        val resultList: List<TotalPriceResult> = mainViewModel.totalPrice(dateId)
        for (result in resultList) {
            val totalPrice = result.totalPrice
            val currency = result.currency
            mapOfCurrencyAndTotalPrice[currency] = totalPrice
        }
    }
    mapOfCurrencyAndTotalPrice.forEach { (currency, totalPrice) ->
        Text("$currency - $totalPrice")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHistogram() {


}