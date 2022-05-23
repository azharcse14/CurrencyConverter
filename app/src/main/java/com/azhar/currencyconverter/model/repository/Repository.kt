package com.azhar.currencyconverter.model.repository

import androidx.lifecycle.LiveData
import com.azhar.currencyconverter.model.data.local.Currency
import com.azhar.currencyconverter.model.data.local.Quote
import com.azhar.currencyconverter.model.data.network.CurrencyList
import com.azhar.currencyconverter.model.data.network.QuoteList
import com.azhar.currencyconverter.model.db.ConverterDao
import com.azhar.currencyconverter.model.db.ConverterDatabase
import com.azhar.currencyconverter.model.network.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class Repository(
    private val converterDatabase: ConverterDatabase,
    private val converterDao: ConverterDao
) {

    fun getCurrencies(): LiveData<List<Currency>> {
        return converterDao.getCurrencies()
    }

    fun getQuoteValue(key: String): Double {
        return converterDao.getQuoteValue(key)
    }

    fun getAllQuote(): List<Quote> {
        return converterDao.getAllQuote()
    }

    fun getCurrencieskey(): LiveData<List<String>> {
        return converterDao.getCurrencieskey()
    }

    fun getCurrenciesValue(): LiveData<List<String>> {
        return converterDao.getCurrenciesValue()
    }

    fun getCurrencyList(): Flow<CurrencyList> = flow {
        val list = RetrofitBuilder.api.getCurrencyList()
        val currencyList = mutableListOf<Currency>()

        for (key in list.currencies.keySet()) {
            currencyList.add(Currency(key, list.currencies[key].asString))
            converterDatabase.converterDao()
                .addCurrencies(Currency(key, list.currencies[key].asString))
        }
        emit(list)
    }.flowOn(Dispatchers.IO)


    fun getQuotes(): Flow<QuoteList> = flow {

        val data = RetrofitBuilder.api.getQuotes(
            "usd",
            "AED, AFN, ALL, AMD, ANG, AOA, ARS, AUD, AWG, AZN, BAM, BBD, BDT, BGN, BHD, BIF, BMD, BND, BOB, BRL, BSD, BTC, BTN, BWP, BYN, BYR, BZD, CAD, CDF, CHF, CLF, CLP, CNY, COP, CRC, CUC, CUP, CVE, CZK, DJF, DKK, DOP, DZD, EGP, ERN, ETB, EUR, FJD, FKP, GBP, GEL, GGP, GHS, GIP, GMD, GNF, GTQ, GYD, HKD, HNL, HRK, HTG, HUF, IDR, ILS, IMP, INR, IQD, IRR, ISK, JEP, JMD, JOD, JPY, KES, KGS, KHR, KMF, KPW, KRW, KWD, KYD, KZT, LAK, LBP, LKR, LRD, LSL, LTL, LVL, LYD, MAD, MDL, MGA, MKD, MMK, MNT, MOP, MRO, MUR, MVR, MWK, MXN, MYR, MZN, NAD, NGN, NIO, NOK, NPR, NZD, OMR, PAB, PEN, PGK, PHP, PKR, PLN, PYG, QAR, RON, RSD, RUB, RWF, SAR, SBD, SCR, SDG, SEK, SGD, SHP, SLL, SOS, SRD, STD, SVC, SYP, SZL, THB, TJS, TMT, TND, TOP, TRY, TTD, TWD, TZS, UAH, UGX, USD, UYU, UZS, VEF, VND, VUV, WST, XAF, XAG, XAU, XCD, XDR, XOF, XPF, YER, ZAR, ZMK, ZMW, ZWL"
        )

        for (key in data.quotes.keySet()) {
            converterDatabase.converterDao()
                .addQuotes(Quote(key.substring(3), data.quotes[key].asDouble))
        }

        emit(data)
    }.flowOn(Dispatchers.IO)
}