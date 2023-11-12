package com.example.budgetapp.pages

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.budgetapp.SelectionOfPages
import com.example.budgetapp.viewmodels.MainViewModel

@Composable
fun TotalPriceOfDates(mainViewModel: MainViewModel, navController: NavHostController) {
    mainViewModel.updateSelection(SelectionOfPages.ADDING)
}