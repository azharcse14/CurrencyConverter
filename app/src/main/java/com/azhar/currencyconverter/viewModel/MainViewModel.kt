package com.azhar.currencyconverter.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azhar.currencyconverter.model.data.local.Currency
import com.azhar.currencyconverter.model.data.local.Quote
import com.azhar.currencyconverter.model.data.network.QuoteList
import com.azhar.currencyconverter.model.repository.Repository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    val quoteList: MutableLiveData<QuoteList> = MutableLiveData()

    fun getCurrencies(): LiveData<List<Currency>> {
        return repository.getCurrencies()
    }

    fun getQuoteValue(key: String): Double {
        return repository.getQuoteValue(key)
    }

    fun getAllQuote(): List<Quote> {
        return repository.getAllQuote()
    }

    fun getCurrencyList() {
        viewModelScope.launch {
            repository.getCurrencyList()
                .catch { e ->
                    Log.d("main", "getCurrencyList: ${e.message}")
                }
                .collect {
                }

        }
    }


    fun getQuotes() {
        viewModelScope.launch {
            repository.getQuotes()
                .catch { e ->
                    Log.d("main", "getQuotes: ${e.message}")
                }
                .collect {
                    quoteList.value = it
                    println(it.quotes.keySet())
                }

        }
    }
}