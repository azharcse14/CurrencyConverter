package com.azhar.currencyconverter.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.azhar.currencyconverter.databinding.ChildQuotesLayoutBinding
import com.azhar.currencyconverter.model.data.local.Quote

class QuotesAdapter : ListAdapter<Quote, QuotesViewHolder>(QuotesDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val binding =
            ChildQuotesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val quotesItem = getItem(position)
        holder.bind(quotesItem)
    }
}