package com.azhar.currencyconverter.model.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class Quote(
    @PrimaryKey

    @ColumnInfo(name = "quote_key")
    var key:String,
    @ColumnInfo(name = "quote_value")
    var value:String
)
