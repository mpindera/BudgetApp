package com.example.budgetapp

sealed class Destination(val route: String) {
    object HomePage : Destination("HomePage")
    object ContentPage : Destination("Content/{dateId}")
    object TotalPage : Destination("TotalPage")
    object AddingDatePage : Destination("AddingDatePage")
    object AddingitemPage : Destination("AddingItemPage/{dateId}")
    object UpdateItemPage : Destination("UpdateItemPage/{itemId}")

}