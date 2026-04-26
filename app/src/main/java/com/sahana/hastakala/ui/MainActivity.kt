package com.sahana.hastakala.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sahana.hastakala.databinding.ActivityMainBinding
import com.sahana.hastakala.utils.DateUtils
import com.sahana.hastakala.viewmodel.SaleViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SaleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        // Today's earnings
        val start = DateUtils.getStartOfDay()
        val end = DateUtils.getEndOfDay()

        viewModel.getTotalIncome(start, end).observe(this) { total ->
            val amount = total ?: 0.0
            binding.tvTodayEarnings.text = "₹ %.2f".format(amount)
        }

        // Today's sales count
        viewModel.getSalesByRange(start, end).observe(this) { sales ->
            val count = sales?.size ?: 0
            binding.tvTodaySalesCount.text = "$count sales today"

            // Stock alert — if any item sold more than 10 times show alert
            if (count > 10) {
                binding.cardStockAlert.visibility = android.view.View.VISIBLE
                binding.tvStockAlert.text = "🔥 High sales today! Make sure stock is ready."
            }
        }

        // Best seller
        viewModel.bestSellers.observe(this) { list ->
            if (!list.isNullOrEmpty()) {
                val best = list[0]
                binding.tvBestSeller.text = "${best.itemName} — ${best.colorOrDesign}"
                binding.tvBestSellerCount.text = "Sold ${best.totalQty} units total"
            } else {
                binding.tvBestSeller.text = "No sales yet"
                binding.tvBestSellerCount.text = ""
            }
        }
    }

    private fun setupClickListeners() {
        // New Sale
        binding.cardNewSale.setOnClickListener {
            startActivity(Intent(this, NewSaleActivity::class.java))
        }

        // Dashboard
        binding.cardDashboard.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }

        // Income Log
        binding.cardIncomeLog.setOnClickListener {
            startActivity(Intent(this, IncomeLogActivity::class.java))
        }

        // Total Sales card
        binding.cardTotalSales.setOnClickListener {
            startActivity(Intent(this, IncomeLogActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh data when coming back from NewSaleActivity
        setupObservers()
    }
}