package com.sahana.hastakala.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SaleDao {

    @Insert
    suspend fun insertSale(sale: SaleEntity)

    @Query("SELECT * FROM sales ORDER BY saleDate DESC")
    fun getAllSales(): LiveData<List<SaleEntity>>

    @Query("SELECT * FROM sales WHERE saleDate BETWEEN :start AND :end ORDER BY saleDate DESC")
    fun getSalesByRange(start: Long, end: Long): LiveData<List<SaleEntity>>

    @Query("SELECT SUM(totalAmount) FROM sales WHERE saleDate BETWEEN :start AND :end")
    fun getTotalIncome(start: Long, end: Long): LiveData<Double>

    @Query("""SELECT itemName, colorOrDesign, SUM(quantity) as totalQty 
              FROM sales GROUP BY itemName, colorOrDesign 
              ORDER BY totalQty DESC""")
    fun getBestSellers(): LiveData<List<BestSellerResult>>
}