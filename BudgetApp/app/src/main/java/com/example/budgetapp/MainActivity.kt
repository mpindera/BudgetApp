package com.example.budgetapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.budgetapp.pages.AddingDatePage
import com.example.budgetapp.pages.AddingItemPage
import com.example.budgetapp.pages.ContentPage
import com.example.budgetapp.pages.HomePage
import com.example.budgetapp.pages.TotalPricePage
import com.example.budgetapp.pages.TotalPriceOfDates
import com.example.budgetapp.pages.UpdateItemPage
import com.example.budgetapp.ui.theme.BudgetAppTheme
import com.example.budgetapp.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
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
                    dateId = id
                )
            }
        }
        composable(Destination.UpdateItemPage.route) { navBackStackEntry ->
            val itemId = navBackStackEntry.arguments?.getString("itemId")?.toIntOrNull()
            itemId?.let { id ->
                UpdateItemPage(
                    mainViewModel = mainViewModel,
                    navController = navController,
                    itemId = id
                )
            }

        }
        composable(Destination.TotalPricePage.route) { navBackStackEntry ->
            val dateId = navBackStackEntry.arguments?.getString("dateId")?.toIntOrNull()
            dateId?.let { id ->
                TotalPricePage(
                    mainViewModel = mainViewModel,
                    navController = navController,
                    dateId = id
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