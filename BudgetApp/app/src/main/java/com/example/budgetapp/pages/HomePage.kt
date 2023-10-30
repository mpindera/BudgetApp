@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.budgetapp.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.budgetapp.Destination
import com.example.budgetapp.TopAppBarTemplate
import com.example.budgetapp.data.DateEntity
import com.example.budgetapp.data.ItemEntity
import com.example.budgetapp.viewmodels.MainViewModel

@Composable
fun HomePage(mainViewModel: MainViewModel, navController: NavHostController) {
    Scaffold(topBar = {
        TopAppBarTemplate()
    }, content = { padding ->
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            CustomList(mainViewModel, padding, navController)
            FloatingActionButton(
                modifier = Modifier
                    .padding(padding.calculateBottomPadding() + 35.dp)
                    .align(Alignment.BottomEnd)
                    .shadow(elevation = 9.dp, shape = RoundedCornerShape(50.dp)),
                containerColor = Color.Gray,
                onClick = {
                    navController.navigate(Destination.AddingPage.route)
                }
            ) {
                Icon(Icons.Default.Add, "")
            }
        }
    })
}

@Composable
fun CustomList(
    mainViewModel: MainViewModel,
    padding: PaddingValues,
    navController: NavHostController
) {
    val dateEntitiesSize = mainViewModel.readAllDataDateEntity.observeAsState(emptyList()).value
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(dateEntitiesSize) { date ->
            Card(modifier = Modifier
                .padding(top = padding.calculateTopPadding() + 8.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth()
                .height(50.dp)
                .clickable {
                    navController.navigate(
                        Destination.ContentPage.route.replace(
                            "{dateId}",
                            date.dateId.toString()
                        )
                    )
                }
                .shadow(elevation = 6.dp, shape = RoundedCornerShape(4.dp))) {
                Text("${date.day} / ${date.month} / ${date.year}")
            }
        }
    }
}
