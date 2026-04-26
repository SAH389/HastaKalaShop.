package com.sahana.hastakala.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sahana.hastakala.data.SaleEntity
import com.sahana.hastakala.databinding.ActivityIncomeLogBinding
import com.sahana.hastakala.databinding.ItemSaleBinding
import com.sahana.hastakala.utils.DateUtils
import com.sahana.hastakala.viewmodel.SaleViewModel
import java.text.SimpleDateFormat
import java.util.*

class IncomeLogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIncomeLogBinding
    private val viewModel: SaleViewModel by viewModels()
    private lateinit var adapter: SaleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncomeLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackIncome.setOnClickListener { finish() } // ← ADD THIS


        setupRecyclerView()
        loadToday()
        setupFilterButtons()
    }

    private fun setupRecyclerView() {
        adapter = SaleAdapter()
        binding.recyclerSales.layoutManager = LinearLayoutManager(this)
        binding.recyclerSales.adapter = adapter
    }

    private fun loadToday() {
        highlightButton("today")
        observeSales(DateUtils.getStartOfDay(), DateUtils.getEndOfDay())
    }

    private fun loadWeek() {
        highlightButton("week")
        observeSales(DateUtils.getStartOfWeek(), DateUtils.getEndOfDay())
    }

    private fun loadMonth() {
        highlightButton("month")
        observeSales(DateUtils.getStartOfMonth(), DateUtils.getEndOfDay())
    }

    private fun observeSales(start: Long, end: Long) {
        viewModel.getSalesByRange(start, end).observe(this) { sales ->
            adapter.submitList(sales ?: emptyList())
        }
        viewModel.getTotalIncome(start, end).observe(this) { total ->
            val amount = total ?: 0.0
            binding.tvTotalIncome.text = "₹ ${"%.2f".format(amount)}"
        }
    }

    private fun setupFilterButtons() {
        binding.btnToday.setOnClickListener { loadToday() }
        binding.btnWeek.setOnClickListener { loadWeek() }
        binding.btnMonth.setOnClickListener { loadMonth() }
    }

    private fun highlightButton(selected: String) {
        val active = ColorStateList.valueOf(Color.parseColor("#1B3A6B"))
        val inactive = ColorStateList.valueOf(Color.parseColor("#CBD5E1"))
        binding.btnToday.backgroundTintList = if (selected == "today") active else inactive
        binding.btnWeek.backgroundTintList = if (selected == "week") active else inactive
        binding.btnMonth.backgroundTintList = if (selected == "month") active else inactive
    }

    // ── RecyclerView Adapter ───────────────────────────────────────────────
    inner class SaleAdapter : RecyclerView.Adapter<SaleAdapter.SaleViewHolder>() {

        private var list = listOf<SaleEntity>()

        fun submitList(newList: List<SaleEntity>) {
            list = newList
            notifyDataSetChanged()
        }

        inner class SaleViewHolder(val b: ItemSaleBinding) :
            RecyclerView.ViewHolder(b.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleViewHolder {
            val b = ItemSaleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return SaleViewHolder(b)
        }

        override fun onBindViewHolder(holder: SaleViewHolder, position: Int) {
            val sale = list[position]
            with(holder.b) {
                tvItemName.text = sale.itemName
                tvColorName.text = sale.colorOrDesign + " • " + sale.quantity + " qty"
                tvAmount.text = "₹" + sale.totalAmount.toInt()
                tvItemIcon.text = getEmoji(sale.itemName)
                val sdf = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
                tvDate.text = sdf.format(Date(sale.saleDate))
            }
        }

        override fun getItemCount() = list.size

        private fun getEmoji(itemName: String): String {
            return when (itemName) {
                "Fiber Bag" -> "🛍️"
                "Keychain" -> "🔑"
                "Basket" -> "🧺"
                "Clay Pot" -> "🏺"
                "Beads" -> "📿"
                else -> "🎀"
            }
        }
    }
}