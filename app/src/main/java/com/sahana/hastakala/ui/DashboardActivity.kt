package com.sahana.hastakala.ui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import com.sahana.hastakala.databinding.FragmentDashboardBinding
import com.sahana.hastakala.viewmodel.SaleViewModel

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: FragmentDashboardBinding
    private val viewModel: SaleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCharts()
        observeData()
        binding.btnBackDashboard.setOnClickListener { finish() } // ← ADD THIS

    }

    private fun setupCharts() {
        // Pie chart basic setup
        binding.pieChart.apply {
            description.isEnabled = false
            isRotationEnabled = true
            legend.isEnabled = true
            setEntryLabelColor(Color.BLACK)
            setEntryLabelTextSize(12f)
        }

        // Bar chart basic setup
        binding.barChart.apply {
            description.isEnabled = false
            legend.isEnabled = true
            xAxis.granularity = 1f
            axisRight.isEnabled = false
        }
    }

    private fun observeData() {
        viewModel.bestSellers.observe(this) { list ->
            if (list.isNullOrEmpty()) return@observe

            // PIE CHART — sales by item
            val pieEntries = mutableListOf<PieEntry>()
            val itemMap = mutableMapOf<String, Int>()
            list.forEach { result ->
                itemMap[result.itemName] =
                    (itemMap[result.itemName] ?: 0) + result.totalQty
            }
            itemMap.forEach { (item, qty) ->
                pieEntries.add(PieEntry(qty.toFloat(), item))
            }

            val pieDataSet = PieDataSet(pieEntries, "Products").apply {
                colors = ColorTemplate.MATERIAL_COLORS.toList()
                valueTextSize = 14f
                valueTextColor = Color.BLACK
            }
            binding.pieChart.data = PieData(pieDataSet)
            binding.pieChart.invalidate()

            // BAR CHART — sales by color
            val barEntries = mutableListOf<BarEntry>()
            val colorMap = mutableMapOf<String, Int>()
            list.forEach { result ->
                colorMap[result.colorOrDesign] =
                    (colorMap[result.colorOrDesign] ?: 0) + result.totalQty
            }
            colorMap.entries.forEachIndexed { index, entry ->
                barEntries.add(BarEntry(index.toFloat(), entry.value.toFloat()))
            }

            val barDataSet = BarDataSet(barEntries, "Colors").apply {
                colors = ColorTemplate.COLORFUL_COLORS.toList()
                valueTextSize = 12f
            }
            binding.barChart.data = BarData(barDataSet)
            binding.barChart.invalidate()
        }
    }
}