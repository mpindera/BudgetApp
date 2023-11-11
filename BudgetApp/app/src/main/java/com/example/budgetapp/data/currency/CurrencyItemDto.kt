package com.example.budgetapp.data.currency


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CurrencyItemDto(
    @SerializedName("name")
    val name: NameDto,
    @SerializedName("cca2")
    val cca2: String,
    @SerializedName("currencies")
    val currencies: Map<String, CurrencyDto>,
    @SerializedName("flags")
    val flags: FlagsDto
)