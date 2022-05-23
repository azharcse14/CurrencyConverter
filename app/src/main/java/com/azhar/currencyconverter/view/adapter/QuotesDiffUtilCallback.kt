package com.azhar.currencyconverter.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.azhar.currencyconverter.model.data.local.Quote

class QuotesDiffUtilCallback : DiffUtil.ItemCallback<Quote>(){
    override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
        return oldItem.key == newItem.key
    }
}