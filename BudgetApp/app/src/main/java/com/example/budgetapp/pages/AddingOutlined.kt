package com.example.budgetapp.pages

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.budgetapp.StaticObjects.itemText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingOutlined(
    modifier: Modifier,
    modifierText: Modifier?,
    valueText: String,
    placeholder: @Composable () -> Unit,
    label: @Composable () -> Unit,
    maxsize: Int?,
    onValChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions?,
    singleLine: Boolean,
    trailingIcon: (@Composable () -> Unit)?,
    readOnly: Boolean?
) {
    if (keyboardOptions != null && readOnly != null) {
        OutlinedTextField(
            value = valueText,
            modifier = modifier,
            singleLine = singleLine,
            placeholder = placeholder,
            label = label,
            onValueChange = onValChange,
            keyboardOptions = keyboardOptions,
            trailingIcon = trailingIcon,
            readOnly = readOnly
        )
    }
    if (modifierText != null) {
        Text(
            modifier = modifierText,
            text = "${valueText.length} / $maxsize"
        )
    }
}
