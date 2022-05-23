package com.azhar.currencyconverter.view.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.azhar.currencyconverter.viewModel.MainViewModel
import com.azhar.currencyconverter.viewModel.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyWorker(private val context: Context, params: WorkerParameters): Worker(context, params) {

    lateinit var mainViewModel: MainViewModel
    @SuppressLint("WrongThread")
    override fun doWork(): Result {
        val repository = (context as ConverterApplication).repository
        val mainViewModelFactory= MainViewModelFactory(repository)
        mainViewModel= ViewModelProvider(ViewModelStore(),mainViewModelFactory)[MainViewModel::class.java]


        CoroutineScope(Dispatchers.IO).launch {
            mainViewModel.getCurrencyList()
            mainViewModel.getQuotes()
        }

        return Result.success()
    }
}