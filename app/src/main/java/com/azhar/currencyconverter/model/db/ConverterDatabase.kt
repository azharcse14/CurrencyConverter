package com.azhar.currencyconverter.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.azhar.currencyconverter.model.data.local.Currency
import com.azhar.currencyconverter.model.data.local.Quote

@Database(entities = [Currency::class, Quote::class], version = 1)
abstract class ConverterDatabase : RoomDatabase() {

    abstract fun converterDao(): ConverterDao

    companion object {
        @Volatile
        private var INSTANCE: ConverterDatabase? = null

        fun getDatabase(context: Context): ConverterDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        ConverterDatabase::class.java,
                        "ConverterDB"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}