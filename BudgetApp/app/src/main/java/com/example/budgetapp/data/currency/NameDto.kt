package com.example.budgetapp.data.currency


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class NameDto(
    @SerializedName("common")
    val common: String
)