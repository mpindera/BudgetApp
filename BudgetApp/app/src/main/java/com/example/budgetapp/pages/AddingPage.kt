package com.example.budgetapp.pages

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.budgetapp.StaticObjects.days
import com.example.budgetapp.StaticObjects.gradientBrush
import com.example.budgetapp.StaticObjects.inputModifier
import com.example.budgetapp.StaticObjects.months
import com.example.budgetapp.StaticObjects.years
import com.example.budgetapp.TopAppBarTemplate
import com.example.budgetapp.data.DateEntity
import com.example.budgetapp.viewmodels.MainViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldRow(
    selectedValue: String, onSelectedItem: (String) -> Unit, modifier: Modifier
) {
    TextField(
        value = selectedValue, onValueChange = {
            onSelectedItem(it)
        }, readOnly = true, modifier = modifier, colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
        ), textStyle = TextStyle(
            fontSize = 15.sp, textAlign = TextAlign.Center
        )
    )
}

@Composable
fun ScrollButtonRow(
    items: List<String>,
    selectedValue: String,
    onSelectedItem: (String) -> Unit,
    label: String,
    borderColor: Color
) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodyLarge,
        fontFamily = FontFamily.Serif,
        modifier = Modifier.padding(
            start = 5.dp, end = 5.dp, bottom = 5.dp, top = 20.dp
        )
    )
    Row() {
        LazyRow {
            items(items) { item ->
                val isSelected = item == selectedValue
                val buttonColor = if (isSelected) borderColor else Color.Black
                OutlinedButton(
                    modifier = Modifier.padding(
                        top = 2.dp, bottom = 2.dp, start = 3.dp, end = 3.dp
                    ), shape = RoundedCornerShape(percent = 15), border = BorderStroke(
                        width = 2.dp, color = Color(0xFF9E723D)
                    ), onClick = {
                        onSelectedItem(item)
                    }, colors = ButtonDefaults.outlinedButtonColors(contentColor = buttonColor)
                ) {
                    Text(text = item, color = Color.Black)
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingPage(mainViewModel: MainViewModel, navController: NavHostController) {
    val context = LocalContext.current
    var dayChosen by remember {
        mutableStateOf("")
    }
    var monthChosen by remember {
        mutableStateOf("")
    }
    var yearChosen by remember {
        mutableStateOf("")
    }
    Scaffold(topBar = {
        TopAppBarTemplate()
    }, content = { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                ) {
                    Text(
                        text = "Adding date",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier
                            .padding(
                                start = 5.dp, end = 5.dp, bottom = 3.dp
                            )
                            .align(Alignment.CenterHorizontally)
                    )
                }

                ScrollButtonRow(
                    items = days,
                    selectedValue = dayChosen,
                    onSelectedItem = { dayChosen = it },
                    label = mainViewModel.myString[0],
                    borderColor = Color(0xFF9E723D)

                )
                ScrollButtonRow(
                    items = months,
                    selectedValue = monthChosen,
                    onSelectedItem = { monthChosen = it },
                    label = mainViewModel.myString[1],
                    borderColor = Color(0xFF423627)
                )
                ScrollButtonRow(
                    items = years,
                    selectedValue = yearChosen,
                    onSelectedItem = { yearChosen = it },
                    label = mainViewModel.myString[2],
                    borderColor = Color(0xFF423627)
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                ) {
                    TextFieldRow(
                        selectedValue = dayChosen,
                        onSelectedItem = { dayChosen = it },
                        modifier = Modifier
                            .weight(0.3f)
                            .padding(start = 15.dp)
                            .then(inputModifier),
                    )
                    TextFieldRow(
                        selectedValue = monthChosen,
                        onSelectedItem = { monthChosen = it },
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(start = 13.dp, end = 13.dp)
                            .then(inputModifier),
                    )
                    TextFieldRow(
                        selectedValue = yearChosen,
                        onSelectedItem = { yearChosen = it },
                        modifier = Modifier
                            .weight(0.3f)
                            .padding(end = 15.dp)
                            .then(inputModifier),
                    )
                }
                Button(
                    onClick = {
                        if (dayChosen.isNotEmpty() && monthChosen.isNotEmpty() && yearChosen.isNotEmpty()) {
                            //mainViewModel.checkIfDateExists(dayChosen, monthChosen, yearChosen)
                            Toast.makeText(context, "Date has been added", Toast.LENGTH_SHORT)
                                .show()
                            mainViewModel.addDate(
                                DateEntity(
                                    day = dayChosen, month = monthChosen, year = yearChosen
                                )
                            )
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 15.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(brush = gradientBrush),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                ) {
                    Text(text = "Add date", color = Color.White)
                }
            }
        }
    })
}
