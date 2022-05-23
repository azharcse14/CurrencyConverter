package com.azhar.currencyconverter.model.data.network

import com.google.gson.JsonObject

data class CurrencyList(
    val currencies: JsonObject,
    val success: Boolean
)
