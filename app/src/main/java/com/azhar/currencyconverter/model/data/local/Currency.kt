package com.azhar.currencyconverter.model.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class Currency(
    @PrimaryKey
    @ColumnInfo
    var key:String,
    @ColumnInfo(name = "value")
    var value:String
)
