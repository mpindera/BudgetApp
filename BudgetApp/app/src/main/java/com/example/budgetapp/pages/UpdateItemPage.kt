package com.example.budgetapp.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.budgetapp.viewmodels.MainViewModel

@Composable
fun UpdateItemPage(
    mainViewModel: MainViewModel,
    navController: NavHostController,
    itemId: Int
) {
    Text(itemId.toString())

}