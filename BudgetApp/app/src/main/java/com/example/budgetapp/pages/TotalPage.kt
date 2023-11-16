package com.example.budgetapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.budgetapp.TopAppBarTemplate
import com.example.budgetapp.dao.TotalPriceResult
import com.example.budgetapp.viewmodels.MainViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun TotalPricePage(mainViewModel: MainViewModel, navController: NavHostController, dateId: Int) {
    val mapOfCurrencyAndTotalPrice = mutableMapOf<String, Double>()
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
            ShowOfTotalPrice(
                mainViewModel = mainViewModel,
                dateId = dateId,
                mapOfCurrencyAndTotalPrice = mapOfCurrencyAndTotalPrice
            )
        }
    }
}

@Composable
fun ShowOfTotalPrice(
    mainViewModel: MainViewModel,
    dateId: Int,
    mapOfCurrencyAndTotalPrice: MutableMap<String, Double>
) {
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