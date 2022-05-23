package com.azhar.currencyconverter.model.network

import com.azhar.currencyconverter.model.data.network.CurrencyList
import com.azhar.currencyconverter.model.data.network.QuoetsList
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("apikey: tf4Uk4xqnXZbxTfkPuIjlftL4VPfPDlA")
    @GET("list")
    suspend fun getCurrencyList() : CurrencyList

    @Headers("apikey: tf4Uk4xqnXZbxTfkPuIjlftL4VPfPDlA")
    @GET("live")
    suspend fun getQuotes(
        @Query("source") source:String,
        @Query("currencies") currencies:String
    ) : QuoetsList
}