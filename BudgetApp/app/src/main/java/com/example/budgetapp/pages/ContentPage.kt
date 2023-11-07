package com.example.budgetapp.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.budgetapp.Destination
import com.example.budgetapp.SelectionOfPages
import com.example.budgetapp.TopAppBarTemplate
import com.example.budgetapp.data.ItemEntity
import com.example.budgetapp.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentPage(mainViewModel: MainViewModel, navController: NavHostController, dateId: Int) {
    mainViewModel.updateSelection(SelectionOfPages.CONTENT)
    val dateEntities = mainViewModel.readAllDataItemEntity.observeAsState(emptyList()).value
    val filteredItems = dateEntities.filter { it.dateId == dateId }

    Scaffold(topBar = {
        TopAppBarTemplate(mainViewModel = mainViewModel, navigationIconClick = {
            navController.popBackStack()
        })
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
        ) {
            ShowListOfItems(filteredItems)
            FloatingActionButton(modifier = Modifier
                .padding(paddingValues.calculateBottomPadding() + 35.dp)
                .align(Alignment.BottomEnd)
                .shadow(elevation = 9.dp, shape = RoundedCornerShape(50.dp)),
                containerColor = Color(0xFFB19775),
                onClick = {
                    navController.navigate(
                        Destination.AddingitemPage.route.replace(
                            "{dateId}", dateId.toString()
                        )
                    )
                }) {
                Icon(Icons.Default.Add, "")
            }
        }
    }
}

@Composable
fun ShowListOfItems(filteredItems: List<ItemEntity>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(filteredItems) { item ->
            Card(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(50.dp)
                .shadow(elevation = 6.dp, shape = RoundedCornerShape(4.dp))) {
                Text("${item.itemName} - ${item.priceOfProduct}")
            }
        }
    }
}
