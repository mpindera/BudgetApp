package com.example.budgetapp.data.currency


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CurrencyDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String
)