package com.azhar.currencyconverter.view.utils

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.azhar.currencyconverter.model.db.ConverterDatabase
import com.azhar.currencyconverter.model.repository.Repository
import java.util.concurrent.TimeUnit

class ConverterApplication: Application() {

    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        initialize()
        setUpWorker()
    }

    private fun setUpWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest = PeriodicWorkRequest.Builder(CurrencyWorker::class.java, 30, TimeUnit.MINUTES)
            .setConstraints(constraint).build()
        WorkManager.getInstance(this).enqueue(workRequest)

    }

    private fun initialize() {
        val database = ConverterDatabase.getDatabase(applicationContext)
        val dao = ConverterDatabase.getDatabase(applicationContext).converterDao()
        repository = Repository(database, dao)
    }
}