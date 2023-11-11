@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.budgetapp.pages

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable


@Composable
fun AlertDialogClear(
    onDissmiss: () -> Unit,
    title: @Composable (() -> Unit),
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable (() -> Unit)

) {
    AlertDialog(
        title = title,
        onDismissRequest = onDissmiss,
        confirmButton = confirmButton,
        dismissButton = dismissButton
    )
}