package com.example.budgetapp.api

import com.example.budgetapp.dao.currency.CurrencyDao
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInstance {
    private val okHttpClient = OkHttpClient()

    val api by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyDao::class.java)
    }
    companion object{
        const val BASE_URL = "https://restcountries.com"
    }
}