package com.example.budgetapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarTemplate(
    navigationIconClick: () -> Unit = {}, actionsClick: () -> Unit = {}
) {
    TopAppBar(navigationIcon = {
        IconButton(onClick = {
            navigationIconClick()
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack, contentDescription = "Menu Icon"
            )
        }
    }, actions = {
        IconButton(onClick = {
            actionsClick()
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack, contentDescription = "Menu Icon"
            )
        }
    }, title = {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Budget Plan", style = MaterialTheme.typography.titleLarge, fontFamily = FontFamily.Serif)
        }
    }, colors = TopAppBarDefaults.smallTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
    )
    )
}