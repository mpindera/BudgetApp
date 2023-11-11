package com.example.budgetapp.data.currency


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class FlagsDto(
    @SerializedName("png")
    val png: String
)