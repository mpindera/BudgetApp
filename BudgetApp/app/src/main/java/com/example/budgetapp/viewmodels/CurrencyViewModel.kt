package com.example.budgetapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetapp.dao.currency.CurrencyDao
import com.example.budgetapp.data.currency.CurrencyItemDto
import kotlinx.coroutines.launch
import java.lang.Exception

class CurrencyViewModel(
    private val api: CurrencyDao,
) : ViewModel() {
    private val _currency = mutableStateOf<List<CurrencyItemDto>>(emptyList())
    val currency: State<List<CurrencyItemDto>> = _currency

    init {
        fetchCurrency()
    }

    private fun fetchCurrency() {
        viewModelScope.launch {
            try {
                val curr = api.getAll()
                _currency.value = curr
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}