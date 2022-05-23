package com.azhar.currencyconverter.model.data.network

import com.google.gson.JsonObject

data class QuoetsList(
    val quotes: JsonObject,
    val source: String,
    val success: Boolean,
    val timestamp: Int
)
