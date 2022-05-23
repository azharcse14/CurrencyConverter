package com.azhar.currencyconverter.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.azhar.currencyconverter.model.data.local.Currency
import com.azhar.currencyconverter.model.data.local.Quote

@Dao
interface ConverterDao {

    @Insert
    suspend fun addCurrencies(currencies: Currency)

    @Query("SELECT * FROM currencies")
    fun getCurrencies() : LiveData<List<Currency>>

    @Query("SELECT 'key' from currencies")
    fun getCurrencieskey() : LiveData<List<String>>

    @Query("SELECT value from currencies")
    fun getCurrenciesValue() : LiveData<List<String>>

    @Insert
    suspend fun addQuotes(quotes: Quote)

    @Query("SELECT * FROM quotes")
    suspend fun getQuotes() : Quote

    @Query("SELECT quote_value FROM quotes where quote_key=:key")
    fun getQuoteValue(key:String) : Double

    @Query("SELECT quote_key, quote_value FROM quotes")
    fun getAllQuote() : List<Quote>

}