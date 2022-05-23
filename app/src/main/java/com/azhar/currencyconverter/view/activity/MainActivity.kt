package com.azhar.currencyconverter.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.azhar.currencyconverter.R
import com.azhar.currencyconverter.databinding.ActivityMainBinding
import com.azhar.currencyconverter.model.data.local.Currency
import com.azhar.currencyconverter.model.data.local.Quote
import com.azhar.currencyconverter.view.adapter.QuotesAdapter
import com.azhar.currencyconverter.view.utils.ConverterApplication
import com.azhar.currencyconverter.viewModel.MainViewModel
import com.azhar.currencyconverter.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var list: java.util.ArrayList<Currency>
    lateinit var mainViewModel: MainViewModel
    lateinit var recyclerAdapter: QuotesAdapter
    var viewList=ArrayList<Quote>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)


        val repository =(application as ConverterApplication).repository

        val mainViewModelFactory=MainViewModelFactory(repository)
        mainViewModel= ViewModelProvider(this,mainViewModelFactory)[MainViewModel::class.java]

        list = ArrayList<Currency>()

        val listString = ArrayList<String>()
        recyclerAdapter= QuotesAdapter()
        findViewById<RecyclerView>(R.id.recycler_view_id).adapter=recyclerAdapter
        mainViewModel.getCurrencies().observe(this, Observer {
            list.addAll(it)
            for(c in list) listString.add(c.value)
            val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_menu_layout,  listString)

            arrayAdapter.notifyDataSetChanged()

            autocompleteTV.setAdapter(arrayAdapter)
        })



        binding.amountInputEtId.requestFocus()
        val imm= getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager
        imm.showSoftInput(autocompleteTV, InputMethodManager.SHOW_IMPLICIT)
        autocompleteTV.setOnClickListener { view ->
            imm.hideSoftInputFromWindow(autocompleteTV.windowToken,0)
        }
        autocompleteTV.setOnItemClickListener { adapterView, view, i, l ->
            if (binding.amountInputEtId.text.toString().trim().isNotEmpty()){
                getConvertion(binding.amountInputEtId.text.toString().toDouble(),list.get(i).key)
            }else{
                Toast.makeText(applicationContext,"Please Enter the Conversion value", Toast.LENGTH_SHORT).show()
            }

        }


    }
    fun getFullName(key:String): String {
        for(c in list) if (c.key.equals(key))return c.value
        return ""
    }
    fun getConvertion(value:Double,currencyString: String){
        Thread(Runnable { var ammount = value
            ammount = ammount/mainViewModel.getQuoteValue(currencyString)
            Log.d("TAG", "onCreate: "+ammount)
            val mainCurrencyList=mainViewModel.getAllQuote()
            viewList.clear()
            for(q in mainCurrencyList){
                viewList.add(Quote(getFullName(q.key),q.value*ammount))
            }
            runOnUiThread {
                recyclerAdapter.submitList(viewList)
                recyclerAdapter.notifyDataSetChanged()
            }
            Log.d("TAG", "getConvertion: "+viewList)

        }).start()

    }
}