package com.example.budgetapp.pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.budgetapp.SelectionOfPages
import com.example.budgetapp.StaticObjects.apiUtil
import com.example.budgetapp.TopAppBarTemplate
import com.example.budgetapp.data.ItemEntity
import com.example.budgetapp.viewmodels.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingItemPage(
    mainViewModel: MainViewModel,
    navController: NavHostController,
    dateId: Int,
) {
    mainViewModel.updateSelection(SelectionOfPages.ADDING)
    val itemEntities = mainViewModel.readAllDataItemEntity.observeAsState(emptyList()).value
    val apiCurrency = apiUtil.currency.value

    var itemNameText by remember {
        mutableStateOf("")
    }
    var priceOfItem by remember {
        mutableStateOf("")
    }
    var categoryText by remember {
        mutableStateOf("")
    }
    var currencyText by remember {
        mutableStateOf("USD")
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
    val context = LocalContext.current
    var showSheet by remember { mutableStateOf(false) }
    val currencyNames = mutableMapOf<String, String>()

    apiCurrency.forEach { currency ->
        currency.currencies?.forEach { (currencyKey, currencyData) ->
            currencyData?.name?.let { currencySymbol ->
                currencyNames[currencyKey] = currencySymbol

            }
        }
    }
    val uniqueCurrencyPairs = currencyNames.entries.distinctBy { it.value }
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

                    //Item Name
                    Text(text = "Item name:", fontFamily = FontFamily.Serif)
                    AddingOutlined(
                        modifier = Modifier.align(Alignment.Start),
                        modifierText = Modifier.align(Alignment.End),
                        valueText = itemNameText,
                        placeholder = {
                            Text("eg. Apple")
                        },
                        label = {
                            Text("Enter Item Name", fontSize = 15.sp)
                        },
                        maxsize = textMaxSize,
                        onValChange = {
                            if (it.length <= textMaxSize) {
                                itemNameText = it
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true,
                        trailingIcon = {},
                        readOnly = false
                    )

                    //item price
                    Text(text = "Item price:", fontFamily = FontFamily.Serif)
                    AddingOutlined(
                        modifier = Modifier.align(Alignment.Start),
                        modifierText = Modifier.align(Alignment.End),
                        valueText = priceOfItem,
                        placeholder = {
                            Text("0.0")
                        },
                        label = {
                            Text("Enter Item Price", fontSize = 15.sp)
                        },
                        maxsize = priceOfItemSize,
                        onValChange = { newText ->
                            val filteredText = newText.filterIndexed { index, char ->
                                char.isDigit() || (char == '.' && newText.indexOf('.') == index)
                            }
                            if (filteredText.length <= priceOfItemSize) {
                                priceOfItem = filteredText
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        trailingIcon = {},
                        readOnly = false
                    )
                }
                //Item Category
                Row() {
                    Column {
                        Text(
                            text = "Category:",
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        AddingOutlined(
                            modifier = Modifier
                                .align(Alignment.Start)
                                .width(200.dp)
                                .padding(start = 10.dp),
                            modifierText = null,
                            valueText = categoryText,
                            placeholder = {
                                Text("eg. Shopping")
                            },
                            label = {
                                Text(text = "Enter Category", fontSize = 15.sp)
                            },
                            maxsize = priceOfItemSize,
                            onValChange = { categoryText = it },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            singleLine = true,
                            trailingIcon = {
                                IconButton(onClick = {
                                    expanded = true
                                }) {
                                    if (expanded) {
                                        Icon(
                                            Icons.Default.KeyboardArrowUp, contentDescription = null
                                        )
                                    } else {
                                        Icon(
                                            Icons.Default.KeyboardArrowDown,
                                            contentDescription = null
                                        )
                                    }
                                }
                            },
                            readOnly = false
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .width(200.dp)
                                .height(100.dp)
                        ) {
                            val uniqueCategories = itemEntities.distinctBy { it.category }
                            for (item in uniqueCategories) {
                                DropdownMenuItem(text = {
                                    Text(item.category.toString())
                                }, onClick = {
                                    categoryText = item.category.toString()
                                    expanded = false
                                })
                            }
                        }
                    }

                    //Item Currency
                    Column() {
                        Text(text = "Currency:", fontFamily = FontFamily.Serif)
                        if (showSheet) {
                            BottomSheet(uniqueCurrencyPairs = uniqueCurrencyPairs,
                                onDismiss = { showSheet = false },
                                onCurrencySelected = { currencyText = it })
                        }
                        AddingOutlined(
                            modifier = Modifier
                                .align(Alignment.Start)
                                .width(150.dp)
                                .padding(start = 10.dp),
                            modifierText = null,
                            valueText = currencyText,
                            placeholder = {
                                Text("eg. USD")
                            },
                            label = {
                                Text(text = "Enter Currency")
                            },
                            maxsize = priceOfItemSize,
                            onValChange = { currencyText = it },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            singleLine = true,
                            trailingIcon = {
                                IconButton(onClick = {
                                    showSheet = true
                                }) {
                                    if (showSheet) {
                                        Icon(
                                            Icons.Default.KeyboardArrowUp, contentDescription = null
                                        )
                                    } else {
                                        Icon(
                                            Icons.Default.KeyboardArrowDown,
                                            contentDescription = null
                                        )
                                    }
                                }
                            },
                            readOnly = true
                        )
                    }
                }

                Button(modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 60.dp, end = 10.dp),
                    onClick = {
                        if (itemNameText.isNotEmpty() && categoryText.isNotEmpty() && priceOfItem.isNotEmpty() && currencyText.isNotEmpty()) {
                            mainViewModel.addItem(
                                ItemEntity(
                                    itemName = itemNameText,
                                    priceOfProduct = priceOfItem.toDouble(),
                                    category = categoryText,
                                    currency = currencyText,
                                    dateId = dateId
                                )
                            )
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
                        }
                    }) {
                    Text("Add Item")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    uniqueCurrencyPairs: List<MutableMap.MutableEntry<String, String>>,
    onDismiss: () -> Unit,
    onCurrencySelected: (String) -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Box(modifier = Modifier.height(300.dp)) {
            ShowCurrency(uniqueCurrencyPairs, onCurrencySelected, onDismiss)
        }
    }
}

@Composable
fun ShowCurrency(
    uniqueCurrencyPairs: List<MutableMap.MutableEntry<String, String>>,
    onCurrencySelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    LazyColumn {
        items(uniqueCurrencyPairs) { item ->
            ClickableText(text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black, fontSize = 19.sp)) {
                    append("${item.key} - ${item.value}")
                }
            }, onClick = {
                onCurrencySelected(item.key)
                onDismiss()
            }, modifier = Modifier
                .padding(9.dp)
                .fillMaxWidth()
            )
        }
    }
}