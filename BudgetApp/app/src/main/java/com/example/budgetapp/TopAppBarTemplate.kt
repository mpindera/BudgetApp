package com.example.budgetapp

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.budgetapp.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarTemplate(
    navigationIconClick: () -> Unit = {},
    mainViewModel: MainViewModel,
    actionIconClick: () -> Unit = {}
) {
    val isAdding = mainViewModel.selection == SelectionOfPages.ADDING
    val isHome = mainViewModel.selection == SelectionOfPages.HOME
    val isContent = mainViewModel.selection == SelectionOfPages.CONTENT
    val isisTotalPricePage = mainViewModel.selection == SelectionOfPages.CONTENT

    TopAppBar(navigationIcon = {
        IconButton(onClick = {
            navigationIconClick()
        }) {
            if (isAdding || isContent || isisTotalPricePage) {
                Icon(
                    imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow back"
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_delete_forever_24),
                    contentDescription = "Clear all"
                )
            }
        }
    }, actions = {
        IconButton(enabled = isHome, onClick = {
            actionIconClick()
        }) {
            if (isHome) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_query_stats_24),
                    contentDescription = "Stats"
                )
            }
        }
    }, title = {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(
                "Budget Plan",
                style = MaterialTheme.typography.titleLarge,
                fontFamily = FontFamily.Serif
            )
        }
    }, colors = TopAppBarDefaults.smallTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
    )
    )
}