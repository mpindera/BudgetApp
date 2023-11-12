package com.example.budgetapp.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.budgetapp.Destination
import com.example.budgetapp.SelectionOfPages
import com.example.budgetapp.TopAppBarTemplate
import com.example.budgetapp.dao.TotalPriceResult
import com.example.budgetapp.data.ItemEntity
import com.example.budgetapp.viewmodels.MainViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun ContentPage(mainViewModel: MainViewModel, navController: NavHostController, dateId: Int) {
    mainViewModel.updateSelection(SelectionOfPages.CONTENT)
    val dateEntities = mainViewModel.readAllDataItemEntity.observeAsState(emptyList()).value
    val filteredItems = dateEntities.filter { it.dateId == dateId }

    Scaffold(
        topBar = {
            TopAppBarTemplate(mainViewModel = mainViewModel, navigationIconClick = {
                navController.popBackStack()
            }, actionIconClick = {
                navController.navigate(
                    Destination.TotalPage.route.replace(
                        "{dateId}", dateId.toString()
                    )
                )
            })
        }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
        ) {
            ShowListOfItems(filteredItems, navController, mainViewModel)
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
fun ShowListOfItems(
    filteredItems: List<ItemEntity>, navController: NavHostController, mainViewModel: MainViewModel
) {
    var isAlertDialogDeleteItemVisible by remember { mutableStateOf(false) }
    var deleteItemDialogItemId by remember { mutableStateOf<Int?>(null) }

    LazyColumn {
        items(filteredItems) { item ->
            Card(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(7.dp))
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .border(0.5.dp, Color.Black, ShapeDefaults.Small),
                colors = CardDefaults.cardColors(
                    containerColor = Color(223, 222, 222, 255)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Column {
                        Text(
                            item.itemName,
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(3.dp),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "${item.priceOfProduct} ${item.currency}",
                            modifier = Modifier
                                .padding(3.dp)
                                .align(Alignment.Start)

                        )
                        Text(
                            item.category.toString(),
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(3.dp),
                        )
                    }
                    Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                        IconButton(onClick = {
                            navController.navigate(
                                Destination.UpdateItemPage.route.replace(
                                    "{itemId}", item.itemId.toString()
                                )
                            )
                        }) {
                            Icon(Icons.Default.Edit, contentDescription = null)
                        }

                        Spacer(modifier = Modifier.padding(1.dp))

                        IconButton(onClick = {
                            isAlertDialogDeleteItemVisible = true
                            deleteItemDialogItemId = item.itemId
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = null)
                        }
                    }

                    if (isAlertDialogDeleteItemVisible) {
                        deleteItemDialogItemId?.let { itemId ->
                            val selectedItem = filteredItems.find { it.itemId == itemId }
                            selectedItem?.let {
                                AlertDialogDeleteItem(
                                    mainViewModel = mainViewModel, itemId = itemId, onDismiss = {
                                        deleteItemDialogItemId = null
                                    }, item = it
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AlertDialogDeleteItem(
    mainViewModel: MainViewModel, itemId: Int, onDismiss: () -> Unit, item: ItemEntity
) {
    AlertDialogClear(onDissmiss = { onDismiss() }, title = {
        Text("Do you want to delete ${item.itemName} | ${item.priceOfProduct} ${item.currency} | ${item.category}?")
    }, confirmButton = {
        Button(onClick = {
            mainViewModel.deleteItem(itemId = itemId)
            onDismiss()
        }) {
            Text("Yes")
        }
    }, dismissButton = {
        Button(onClick = {
            onDismiss()
        }) {
            Text("Cancel")
        }
    })
}