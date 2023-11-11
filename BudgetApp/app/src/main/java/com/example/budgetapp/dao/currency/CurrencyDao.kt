package com.example.budgetapp.dao.currency

import com.example.budgetapp.data.currency.Currency
import com.example.budgetapp.data.currency.CurrencyItemDto
import retrofit2.http.GET

interface CurrencyDao {
    @GET("/v3.1/all")
    suspend fun getAll(): List<CurrencyItemDto>

}