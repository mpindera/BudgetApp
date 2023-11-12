package com.example.budgetapp

sealed class Destination(val route: String) {
    object HomePage : Destination("HomePage")
    object ContentPage : Destination("Content/{dateId}")
    object TotalPage : Destination("TotalPage/{dateId}")
    object AddingDatePage : Destination("AddingDatePage")
    object AddingitemPage : Destination("AddingItemPage/{dateId}")
    object UpdateItemPage : Destination("UpdateItemPage/{itemId}")
    object TotalPriceOfDates : Destination("TotalPriceOfDates/{itemId}")

}