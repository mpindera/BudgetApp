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
import com.example.budgetapp.content_page.ContentPage
import com.example.budgetapp.home_page.HomePage
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
                NavigationAppHost(navController = navController, mainViewModel = mainViewModel)

            }
        }
    }
}

@Composable
fun NavigationAppHost(navController: NavHostController, mainViewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = Destination.HomePage.route) {
        composable(Destination.HomePage.route) {
            HomePage(mainViewModel, navController)
        }
        composable(Destination.ContentPage.route) { navBackStackEntry ->
            val dateId = navBackStackEntry.arguments?.getString("dateId")?.toIntOrNull()
            dateId?.let { id ->
                ContentPage(
                    mainViewModel = mainViewModel,
                    navController = navController,
                    dateId = id
                )
            }
        }
        composable(Destination.TotalPage.route) { navBackStackEntry ->


        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BudgetAppTheme {
        MainActivity()
    }
}/*Column {
                        Button(onClick = {
                            mainVm.addDate(DateEntity(1, 111))
                            mainVm.addDate(DateEntity(2, 222))

                            mainVm.addItem(ItemEntity(1, "a", 1.2, 1))
                            mainVm.addItem(ItemEntity(2, "b", 1.3, 1))
                            mainVm.addItem(ItemEntity(3, "c", 1.4, 2))
                        }) {

                        }
                    }
                    val itemEntities = mainVm.readAllDataDateEntity.observeAsState(emptyList()).value
                    LazyColumn {
                        items(itemEntities){ col ->

                        }
                    }*/