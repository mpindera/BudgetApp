package com.example.budgetapp.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.budgetapp.SelectionOfPages
import com.example.budgetapp.dao.TotalPriceResult
import com.example.budgetapp.data.DateEntity
import com.example.budgetapp.viewmodels.MainViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun TotalPriceOfDates(mainViewModel: MainViewModel, navController: NavHostController) {
    mainViewModel.updateSelection(SelectionOfPages.ADDING)
    val dates = mainViewModel.readAllDataDateEntity.value
    RangeSliderOfPrices(dates = dates, mainViewModel = mainViewModel)


}

@Composable
fun ShowMax(mapOfCurrencyAndTotalPrice: MutableMap<String, Double>, visibility: Boolean) {
    val currencyAndPrice: List<Pair<String, Double>> = mapOfCurrencyAndTotalPrice.toList()

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        itemsIndexed(currencyAndPrice) { index, (currency, totalPrice) ->
            val delayMillis = 200 * index
            CardForPrices(
                currency = currency,
                totalPrice = totalPrice.toString(),
                visibility = visibility,
                delayMillis = delayMillis
            )
        }
    }
}


@Composable
fun RangeSliderOfPrices(dates: List<DateEntity>?, mainViewModel: MainViewModel) {
    var visibility by remember { mutableStateOf(false) }
    val mapOfCurrencyAndTotalPrice = mutableMapOf<String, Double>()
    if (!dates.isNullOrEmpty()) {
        val existingDates = dates.map { it.dateId.toFloat() }
        println(existingDates)
        val minDate = existingDates.min()
        val maxDate = existingDates.max()

        var sliderValues by remember {
            mutableStateOf(minDate..maxDate)
        }
        val startDate = getDateFromId(sliderValues.start.toInt(), dates)
        val endDate = getDateFromId(sliderValues.endInclusive.toInt(), dates)

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            RangeSlider(value = sliderValues, onValueChange = { newValues ->
                sliderValues = newValues
                visibility = false
            }, onValueChangeFinished = {
                if (startDate == null || endDate == null) {
                    sliderValues = minDate..maxDate
                } else {
                    visibility = true
                }

            }, valueRange = minDate..maxDate, steps = existingDates.size - 1
            )

            if (startDate != null && endDate != null) {
                Column {
                    Text(text = "Start: ${startDate.day}/${startDate.month}/${startDate.year}")
                    Text(text = "End: ${endDate.day}/${endDate.month}/${endDate.year}")
                }

                runBlocking {
                    val resultList: List<TotalPriceResult> =
                        mainViewModel.totalPriceOfAllSelectedDate(startDate.dateId, endDate.dateId)
                    for (result in resultList) {
                        val totalPrice = result.totalPrice
                        val currency = result.currency
                        mapOfCurrencyAndTotalPrice[currency] = totalPrice
                    }
                }
                ShowMax(mapOfCurrencyAndTotalPrice, visibility)
            }

        }
    } else {
        Text(text = "No dates available")
    }
}

fun getDateFromId(toInt: Int, dates: List<DateEntity>): DateEntity? {
    return dates.find { it.dateId == toInt }
}

@Composable
fun CardForPrices(
    currency: String,
    totalPrice: String,
    visibility: Boolean,
    delayMillis: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp)

    ) {
        AnimatedVisibility(
            visible = visibility,
            enter = fadeIn(

            ),
            exit = fadeOut()
        ) {
            Card(
                elevation = CardDefaults.cardElevation(10.dp),
                modifier = Modifier.fillMaxWidth().animateContentSize(),
            ) {
                Column(modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally)) {
                    Text(currency, fontWeight = FontWeight.W700)
                    Text(totalPrice)
                }
            }
        }
    }
}
