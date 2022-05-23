package com.azhar.currencyconverter.model.network

import com.azhar.currencyconverter.model.data.network.CurrencyList
import com.azhar.currencyconverter.model.data.network.QuoteList
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("apikey: 8hfNZ1wKRuiF0FyqXYiXjXidtKV3cggV")
    @GET("list")
    suspend fun getCurrencyList(): CurrencyList

    @Headers("apikey: 8hfNZ1wKRuiF0FyqXYiXjXidtKV3cggV")
    @GET("live")
    suspend fun getQuotes(
        @Query("source") source: String,
        @Query("currencies") currencies: String
    ): QuoteList
}