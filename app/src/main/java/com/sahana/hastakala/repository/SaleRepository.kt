package com.sahana.hastakala.repository

import android.content.Context
import com.sahana.hastakala.data.AppDatabase
import com.sahana.hastakala.data.SaleEntity

class SaleRepository(context: Context) {
    private val dao = AppDatabase.getDatabase(context).saleDao()

    val allSales = dao.getAllSales()
    val bestSellers = dao.getBestSellers()

    suspend fun insertSale(sale: SaleEntity) = dao.insertSale(sale)

    fun getSalesByRange(start: Long, end: Long) = dao.getSalesByRange(start, end)
    fun getTotalIncome(start: Long, end: Long) = dao.getTotalIncome(start, end)
}