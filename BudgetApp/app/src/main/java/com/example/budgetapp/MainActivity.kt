package com.example.budgetapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.budgetapp.pages.addingpages.AddingDatePage
import com.example.budgetapp.pages.addingpages.AddingItemPage
import com.example.budgetapp.pages.ContentPage
import com.example.budgetapp.pages.HomePage
import com.example.budgetapp.pages.TotalPriceOfDates
import com.example.budgetapp.ui.theme.BudgetAppTheme
import com.example.budgetapp.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BudgetAppTheme {
                val navController = rememberNavController()
                NavigationAppHost(
                    navController = navController,
                    mainViewModel = mainViewModel,
                )

            }
        }
    }
}

@Composable
fun NavigationAppHost(
    navController: NavHostController,
    mainViewModel: MainViewModel,
) {
    NavHost(navController = navController, startDestination = Destination.HomePage.route) {
        composable(Destination.HomePage.route) {
            HomePage(mainViewModel, navController)
        }
        composable(Destination.ContentPage.route) { navBackStackEntry ->
            val dateId = navBackStackEntry.arguments?.getString("dateId")?.toIntOrNull()
            dateId?.let { id ->
                ContentPage(
                    mainViewModel = mainViewModel, navController = navController, dateId = id
                )
            }
        }
        composable(Destination.AddingDatePage.route) {
            AddingDatePage(mainViewModel, navController)
        }
        composable(Destination.AddingitemPage.route) { navBackStackEntry ->
            val dateId = navBackStackEntry.arguments?.getString("dateId")?.toIntOrNull()
            dateId?.let { id ->
                AddingItemPage(
                    mainViewModel = mainViewModel,
                    navController = navController,
                    dateId = id,
                )
            }
        }
        composable(Destination.TotalPriceOfDates.route) { navBackStackEntry ->
            TotalPriceOfDates(
                mainViewModel = mainViewModel,
                navController = navController,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BudgetAppTheme {
        MainActivity()
    }
}