package com.sahana.hastakala.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales")
data class SaleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemName: String,
    val colorOrDesign: String,
    val quantity: Int = 1,
    val pricePerUnit: Double,
    val totalAmount: Double,
    val saleDate: Long = System.currentTimeMillis()
)