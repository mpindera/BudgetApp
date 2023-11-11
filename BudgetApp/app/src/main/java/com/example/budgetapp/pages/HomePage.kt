@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.budgetapp.pages

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.budgetapp.Destination
import com.example.budgetapp.R
import com.example.budgetapp.SelectionOfPages
import com.example.budgetapp.TopAppBarTemplate
import com.example.budgetapp.data.DateEntity
import com.example.budgetapp.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(mainViewModel: MainViewModel, navController: NavHostController) {
    var isAlertDialogAllVisible by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBarTemplate(mainViewModel = mainViewModel, actionIconClick = {
            isAlertDialogAllVisible = true
        })
    }, content = { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding()),
        ) {
            mainViewModel.updateSelection(SelectionOfPages.HOME)
            CustomList(mainViewModel, padding, navController)
            FloatingActionButton(modifier = Modifier
                .padding(padding.calculateBottomPadding() + 35.dp)
                .align(Alignment.BottomEnd)
                .shadow(elevation = 9.dp, shape = RoundedCornerShape(50.dp)),
                containerColor = Color(0xFFB19775),
                onClick = {
                    navController.navigate(Destination.AddingDatePage.route)
                }) {
                Icon(Icons.Default.Add, "")
            }
        }
    })
    if (isAlertDialogAllVisible) {
        AlertDialogClearAll(mainViewModel) {
            isAlertDialogAllVisible = false
        }
    }
}

@Composable
fun CustomList(
    mainViewModel: MainViewModel,
    padding: PaddingValues,
    navController: NavHostController,
) {
    var isAlertDialogOneOfDateVisible by remember { mutableStateOf(false) }
    val dateEntitiesSize = mainViewModel.readAllDataDateEntity.observeAsState(emptyList()).value
    var deleteDateDialogDateId by remember {
        mutableIntStateOf(0)
    }


    LazyVerticalGrid(columns = GridCells.Fixed(1)) {

        items(dateEntitiesSize) { date ->
            val textOfSize =
                if (mainViewModel.getItemsCountForDate(date.dateId) > 1) "Items" else "Item"
            Card(
                modifier = Modifier
                    .padding(
                        top = 15.dp, start = 8.dp, end = 8.dp
                    )
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(shape = RoundedCornerShape(3.dp))
                    .border(0.3.dp, Color.Black, ShapeDefaults.Medium)
                    .clickable {
                        navController.navigate(
                            Destination.ContentPage.route.replace(
                                "{dateId}", date.dateId.toString()
                            )
                        )
                    }, colors = CardDefaults.cardColors(containerColor = Color(0xFFE4DED6))
            ) {
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
                        text = "${mainViewModel.getItemsCountForDate(date.dateId)} $textOfSize"
                    )

                    IconButton(onClick = {
                        isAlertDialogOneOfDateVisible = true
                        deleteDateDialogDateId = date.dateId
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_delete_24),
                            contentDescription = null
                        )
                    }

                    if (isAlertDialogOneOfDateVisible) {
                        deleteDateDialogDateId?.let { dateId ->
                            val selectedItem = dateEntitiesSize.find { it.dateId == dateId }
                            selectedItem?.let {
                                AlertDialogClearOneOfDateVisible(
                                    mainViewModel = mainViewModel,
                                    date=it,
                                    dateId=dateId
                                ) {
                                    isAlertDialogOneOfDateVisible = false

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AlertDialogClearOneOfDateVisible(
    mainViewModel: MainViewModel, date: DateEntity, dateId: Int, onDismiss: () -> Unit
) {
    AlertDialogClear(onDissmiss = { onDismiss() },
        title = { Text("Do you want to delete ${date.day}/${date.month}/${date.year}?") },
        confirmButton = {
            Button(onClick = {
                mainViewModel.deleteDate(dateId = dateId)
                onDismiss()
            }) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }) {
                Text("Cancel")
            }
        })
}

@Composable
fun AlertDialogClearAll(mainViewModel: MainViewModel, onDismiss: () -> Unit) {
    AlertDialogClear(
        onDissmiss = { onDismiss() },
        title = { Text("Do you want to delete everything?") },
        confirmButton = {
            Button(onClick = {
                mainViewModel.dropItemDataBase()
                mainViewModel.dropDateDataBase()

                onDismiss()
            }) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }) {
                Text("Cancel")
            }
        }
    )
}