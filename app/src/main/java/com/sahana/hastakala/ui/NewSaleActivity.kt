package com.sahana.hastakala.ui

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sahana.hastakala.data.SaleEntity
import com.sahana.hastakala.databinding.ActivityNewSaleBinding
import com.sahana.hastakala.viewmodel.SaleViewModel

class NewSaleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewSaleBinding
    private val viewModel: SaleViewModel by viewModels()

    private var selectedItem = ""
    private var selectedColor = ""

    // Items list with emojis
    private val items = listOf(
        "🛍️" to "Fiber Bag",
        "🔑" to "Keychain",
        "🧺" to "Basket",
        "🏺" to "Clay Pot",
        "📿" to "Beads",
        "🎀" to "Craft Item"
    )

    // Colors list
    private val colors = listOf(
        "🔴" to "Red",
        "🔵" to "Blue",
        "🟢" to "Green",
        "🟡" to "Yellow",
        "🟣" to "Purple",
        "⚫" to "Black"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewSaleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupItemGrid()
        setupColorGrid()
        setupClickListeners()
    }

    private fun setupItemGrid() {
        items.forEach { (emoji, name) ->
            val btn = createGridButton(emoji, name)
            btn.setOnClickListener {
                selectedItem = name
                // Reset all buttons
                resetItemButtons()
                btn.setBackgroundColor(Color.parseColor("#1B3A6B"))
                btn.setTextColor(Color.WHITE)
                Toast.makeText(this, "$emoji $name selected!", Toast.LENGTH_SHORT).show()
            }
            binding.gridItems.addView(btn)
        }
    }

    private fun setupColorGrid() {
        colors.forEach { (emoji, name) ->
            val btn = createGridButton(emoji, name)
            btn.setOnClickListener {
                selectedColor = name
                resetColorButtons()
                btn.setBackgroundColor(Color.parseColor("#0E7C7B"))
                btn.setTextColor(Color.WHITE)
                Toast.makeText(this, "$emoji $name selected!", Toast.LENGTH_SHORT).show()
            }
            binding.gridColors.addView(btn)
        }
    }

    private fun createGridButton(emoji: String, name: String): Button {
        val btn = Button(this)
        btn.text = "$emoji\n$name"
        btn.textSize = 12f
        btn.setBackgroundColor(Color.parseColor("#F0F4F8"))
        btn.setTextColor(Color.parseColor("#1B3A6B"))
        btn.setPadding(8, 16, 8, 16)

        val params = GridLayout.LayoutParams()
        params.width = 0
        params.height = GridLayout.LayoutParams.WRAP_CONTENT
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
        params.setMargins(6, 6, 6, 6)
        btn.layoutParams = params
        return btn
    }

    private fun resetItemButtons() {
        for (i in 0 until binding.gridItems.childCount) {
            val btn = binding.gridItems.getChildAt(i) as Button
            btn.setBackgroundColor(Color.parseColor("#F0F4F8"))
            btn.setTextColor(Color.parseColor("#1B3A6B"))
        }
    }

    private fun resetColorButtons() {
        for (i in 0 until binding.gridColors.childCount) {
            val btn = binding.gridColors.getChildAt(i) as Button
            btn.setBackgroundColor(Color.parseColor("#F0F4F8"))
            btn.setTextColor(Color.parseColor("#1B3A6B"))
        }
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener { finish() }

        binding.btnSaveSale.setOnClickListener {
            val priceText = binding.etPrice.text.toString()

            when {
                selectedItem.isEmpty() -> {
                    Toast.makeText(this, "⚠️ Please select an item!", Toast.LENGTH_SHORT).show()
                }
                selectedColor.isEmpty() -> {
                    Toast.makeText(this, "⚠️ Please select a color!", Toast.LENGTH_SHORT).show()
                }
                priceText.isEmpty() -> {
                    Toast.makeText(this, "⚠️ Please enter a price!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val price = priceText.toDouble()
                    val sale = SaleEntity(
                        itemName = selectedItem,
                        colorOrDesign = selectedColor,
                        quantity = 1,
                        pricePerUnit = price,
                        totalAmount = price
                    )
                    viewModel.insertSale(sale)
                    Toast.makeText(this, "✅ Sale saved successfully!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}