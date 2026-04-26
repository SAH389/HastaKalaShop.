package com.sahana.hastakala.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sahana.hastakala.data.SaleEntity
import com.sahana.hastakala.repository.SaleRepository
import kotlinx.coroutines.launch

class SaleViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SaleRepository(application)

    val allSales = repository.allSales
    val bestSellers = repository.bestSellers

    fun insertSale(sale: SaleEntity) {
        viewModelScope.launch {
            repository.insertSale(sale)
        }
    }

    fun getSalesByRange(start: Long, end: Long) =
        repository.getSalesByRange(start, end)

    fun getTotalIncome(start: Long, end: Long) =
        repository.getTotalIncome(start, end)
}