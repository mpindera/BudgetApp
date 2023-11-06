package com.example.budgetapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.budgetapp.SelectionOfPages
import com.example.budgetapp.TopAppBarTemplate
import com.example.budgetapp.viewmodels.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingItemPage(mainViewModel: MainViewModel, navController: NavHostController, dateId: Int) {
    mainViewModel.updateSelection(SelectionOfPages.ADDING)
    val textMaxSize by remember {
        mutableIntStateOf(35)
    }
    var textItemName by remember {
        mutableStateOf("")
    }
    var priceOfItem by remember {
        mutableDoubleStateOf(0.0)
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
                modifier = Modifier.padding(
                    top = paddingValues.calculateTopPadding(),
                    start = 5.dp
                )
            ) {
                Text(text = "Item Name:", fontFamily = FontFamily.Serif)
                OutlinedTextField(value = textItemName,
                    modifier = Modifier.align(Alignment.Start),
                    singleLine = true,
                    label = {
                        Text(text = "Enter Item Name")
                    },
                    onValueChange = {
                        if (it.length <= textMaxSize) {
                            textItemName = it
                        }
                    })
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = "${textItemName.length} / $textMaxSize"
                )
            }
        }
    }
}