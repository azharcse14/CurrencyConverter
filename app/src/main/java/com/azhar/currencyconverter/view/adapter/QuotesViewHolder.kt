package com.azhar.currencyconverter.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.azhar.currencyconverter.databinding.ChildQuotesLayoutBinding
import com.azhar.currencyconverter.model.data.local.Quote
import java.math.RoundingMode
import java.text.DecimalFormat

class QuotesViewHolder(val binding: ChildQuotesLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val amount = binding.amountTextTvId
    val name = binding.nameTextTvId

    fun bind(item: Quote) {
        val df = DecimalFormat("#.###")
        val roundValue = df.format(item.value)
        amount.text = roundValue
        name.text = item.key
    }
}