@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.budgetapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor
import androidx.navigation.NavHostController
import com.example.budgetapp.Destination
import com.example.budgetapp.R
import com.example.budgetapp.TopAppBarTemplate
import com.example.budgetapp.data.DateEntity
import com.example.budgetapp.data.ItemEntity
import com.example.budgetapp.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(mainViewModel: MainViewModel, navController: NavHostController) {
    Scaffold(topBar = {
        TopAppBarTemplate()
    }, content = { padding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(top=padding.calculateTopPadding()),
        ) {
            CustomList(mainViewModel, padding, navController)
            FloatingActionButton(modifier = Modifier
                .padding(padding.calculateBottomPadding() + 35.dp)
                .align(Alignment.BottomEnd)
                .shadow(elevation = 9.dp, shape = RoundedCornerShape(50.dp)),
                containerColor = Color.Gray,
                onClick = {
                    navController.navigate(Destination.AddingPage.route)
                }) {
                Icon(Icons.Default.Add, "")
            }
        }
    })
}

@Composable
fun CustomList(
    mainViewModel: MainViewModel, padding: PaddingValues, navController: NavHostController
) {
    val dateEntitiesSize = mainViewModel.readAllDataDateEntity.observeAsState(emptyList()).value
    val itemsSize = mainViewModel.readAllDataItemEntity.observeAsState(emptyList()).value
    LazyVerticalGrid(columns = GridCells.Fixed(1)) {
        val textOfSize = if (itemsSize.size > 1) "Items" else "Item"
        items(dateEntitiesSize) { date ->
            Card(modifier = Modifier
                .padding(
                    top =  15.dp, start = 8.dp, end = 8.dp
                )
                .fillMaxWidth()
                .height(50.dp)
                .shadow(elevation = 6.dp, shape = RoundedCornerShape(4.dp))
                .clickable {
                    navController.navigate(
                        Destination.ContentPage.route.replace(
                            "{dateId}", date.dateId.toString()
                        )
                    )
                }) {
                Box(
                    contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(
                        modifier = Modifier
                            .background(Color(date.colorOfSpacer))
                            .width(10.dp)
                            .fillMaxHeight()
                            .align(Alignment.CenterStart)
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(top = 2.dp, bottom = 2.dp, start = 15.dp),
                        fontWeight = FontWeight.Bold,
                        text = "${date.day} / ${date.month} / ${date.year}"
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(top = 2.dp, bottom = 2.dp, start = 15.dp),
                        text = "${itemsSize.size} $textOfSize"
                    )
                    IconButton(onClick = {
                        mainViewModel.dropItemDataBase()
                        mainViewModel.dropDateDataBase()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_delete_24),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Prev() {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .height(50.dp)
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(4.dp))
    ) {
        Box(
            contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxSize()
        ) {
            Spacer(
                modifier = Modifier
                    .background(Color.Green)
                    .width(10.dp)
                    .fillMaxHeight()
                    .align(Alignment.CenterStart)
            )

            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 2.dp, bottom = 2.dp, start = 15.dp),
                fontWeight = FontWeight.Bold,
                text = "03 / 07 / 2023"
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(top = 2.dp, bottom = 2.dp, start = 15.dp), text = "2 Items"
            )
            IconButton(onClick = {

            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_delete_24),
                    contentDescription = null
                )
            }


        }

    }
}