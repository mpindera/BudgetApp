package com.example.budgetapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.budgetapp.SelectionOfPages
import com.example.budgetapp.TopAppBarTemplate
import com.example.budgetapp.data.ItemEntity
import com.example.budgetapp.viewmodels.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingItemPage(mainViewModel: MainViewModel, navController: NavHostController, dateId: Int) {
    mainViewModel.updateSelection(SelectionOfPages.ADDING)
    val itemEntities = mainViewModel.readAllDataItemEntity.observeAsState(emptyList()).value

    var itemNameText by remember {
        mutableStateOf("")
    }
    var priceOfItem by remember {
        mutableStateOf("")
    }
    var categoryText by remember {
        mutableStateOf("")
    }
    val textMaxSize by remember {
        mutableIntStateOf(35)
    }
    val priceOfItemSize by remember {
        mutableIntStateOf(10)
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    Scaffold(topBar = {
        TopAppBarTemplate(mainViewModel = mainViewModel, navigationIconClick = {
            navController.popBackStack()
        })
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE2B277))
                ) {
                    Text(
                        text = "Adding item",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier
                            .padding(
                                start = 5.dp, end = 5.dp, bottom = 3.dp
                            )
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding()
                    )
            ) {
                Column(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Item name:", fontFamily = FontFamily.Serif)
                    OutlinedTextField(value = itemNameText,
                        modifier = Modifier.align(Alignment.Start),
                        singleLine = true,
                        placeholder = { Text("Apple") },
                        label = {
                            Text(text = "Enter Item Name")
                        },
                        onValueChange = {
                            if (it.length <= textMaxSize) {
                                itemNameText = it
                            }
                        })
                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = "${itemNameText.length} / $textMaxSize"
                    )

                    Text(text = "Item price:", fontFamily = FontFamily.Serif)
                    OutlinedTextField(value = priceOfItem,
                        modifier = Modifier.align(Alignment.Start),
                        placeholder = { Text("0.0") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        label = {
                            Text(text = "Enter Item Price")
                        },
                        onValueChange = { newText ->
                            val filteredText = newText.filterIndexed { index, char ->
                                char.isDigit() || (char == '.' && newText.indexOf('.') == index)
                            }
                            if (filteredText.length <= priceOfItemSize) {
                                priceOfItem = filteredText
                            }
                        })
                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = "${priceOfItem.length} / $priceOfItemSize"
                    )

                    Text(text = "Category:", fontFamily = FontFamily.Serif)
                    OutlinedTextField(value = categoryText,
                        modifier = Modifier.align(Alignment.Start),
                        placeholder = { Text("eg. Shopping") },
                        singleLine = true,
                        trailingIcon = {
                            IconButton(onClick = {
                                expanded = true
                            }) {
                                if (expanded) {
                                    Icon(Icons.Default.KeyboardArrowUp, contentDescription = null)
                                } else {
                                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
                                }
                            }
                        },
                        label = {
                            Text(text = "Enter Category")
                        },
                        onValueChange = {
                            categoryText = it
                        })

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .width(200.dp)
                            .height(100.dp)
                    ) {
                        for (item in itemEntities) {
                            DropdownMenuItem(text = {
                                Text(item.category.toString())
                            }, onClick = {
                                categoryText = item.category.toString()
                                expanded = false
                            })
                        }
                    }
                }

                Button(modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 60.dp, end = 10.dp),
                    onClick = {
                        if (itemNameText.isNotEmpty() && categoryText.isNotEmpty() && priceOfItem.isNotEmpty()) {
                            mainViewModel.addItem(
                                ItemEntity(
                                    itemName = itemNameText,
                                    priceOfProduct = priceOfItem.toDouble(),
                                    category = categoryText,
                                    dateId = dateId
                                )
                            )
                        }
                    }) {
                    Text("Add Item")
                }
            }
        }
    }
}