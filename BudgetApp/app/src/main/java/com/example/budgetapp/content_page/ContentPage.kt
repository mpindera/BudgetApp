package com.example.budgetapp.content_page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.budgetapp.Destination
import com.example.budgetapp.viewmodels.MainViewModel

@Composable
fun ContentPage(mainViewModel: MainViewModel, navController: NavHostController, dateId: Int) {
    val dateEntities = mainViewModel.readAllData.observeAsState(emptyList()).value
    val filteredItems = dateEntities.filter { it.dateId == dateId }
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(filteredItems) { item ->
            Card(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(50.dp)
                .clickable {

                }
                .shadow(elevation = 6.dp, shape = RoundedCornerShape(4.dp))) {
                Text("${item.itemName} - ${item.priceOfProduct}")
            }
        }
    }
}