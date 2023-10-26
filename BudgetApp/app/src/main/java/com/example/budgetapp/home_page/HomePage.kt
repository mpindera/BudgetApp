@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.budgetapp.home_page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.budgetapp.data.DateEntity
import com.example.budgetapp.data.ItemEntity
import com.example.budgetapp.viewmodels.MainViewModel

@Composable
fun HomePage(mainViewModel: MainViewModel) {
    val dateEntitiesSize = mainViewModel.readAllDataDateEntity.observeAsState(emptyList()).value

    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    mainViewModel.addDate(DateEntity(1, 1, "Styczeń", 2001))
                    mainViewModel.addDate(DateEntity(2, 12, "Luty", 2023))

                    mainViewModel.addItem(ItemEntity(1, "a", 1.2, 1))
                    mainViewModel.addItem(ItemEntity(2, "b", 1.3, 1))
                    mainViewModel.addItem(ItemEntity(3, "c", 1.4, 2))

                }) {
                    Icon(
                        imageVector = Icons.Default.Menu, contentDescription = "Menu Icon"
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    mainViewModel.dropDateDataBase()
                    mainViewModel.dropItemDataBase()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack, contentDescription = "Menu Icon"
                    )
                }
            },
            title = {
                Text("Home")
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
            )
        )

    }, content = { padding ->
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            CustomList(mainViewModel, padding)
            FloatingActionButton(
                modifier = Modifier
                    .padding(padding.calculateBottomPadding() + 35.dp)
                    .align(Alignment.BottomEnd)
                    .shadow(elevation = 9.dp, shape = RoundedCornerShape(50.dp)),
                containerColor = Color.Gray,
                onClick = {

                }
            ) {
                Icon(Icons.Default.Add, "")
            }
        }
    })
}

@Composable
fun CustomList(mainViewModel: MainViewModel, padding: PaddingValues) {
    val dateEntitiesSize = mainViewModel.readAllDataDateEntity.observeAsState(emptyList()).value
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(dateEntitiesSize) { date ->
            Card(modifier = Modifier
                .padding(top = padding.calculateTopPadding() + 8.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth()
                .height(50.dp)
                .clickable {

                }
                .shadow(elevation = 6.dp, shape = RoundedCornerShape(4.dp))) {
                Text("${date.day} / ${date.month} / ${date.year}")
            }
        }
    }

}

/*
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(dateEntitiesSize) {
                Card(modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {

                    }
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(4.dp))) {
                    Text("hello")
                }
            }
        }

*/