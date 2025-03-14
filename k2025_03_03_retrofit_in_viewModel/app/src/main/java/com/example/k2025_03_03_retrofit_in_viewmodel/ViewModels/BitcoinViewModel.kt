package com.example.k2025_03_03_retrofit_in_viewmodel.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class BitcoinViewModel: ViewModel() {
    private lateinit var bitcoinRestInterface: BitcoinApiService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.0.2.2:3003/bitcoin/")
            .build()
        Log.i("PGB", "Endpoint built")
        bitcoinRestInterface = retrofit.create(BitcoinApiService::class.java)
    }

    fun getOneBitcoin() {
        viewModelScope.launch {
            try {
                val bitcoin: Bitcoin = bitcoinRestInterface.getBitcoin()
                Log.i("PGB", "My Bitcoin 2: " + bitcoin.name + " " + bitcoin.hash)
            } catch(e: Exception) {
                e.printStackTrace()
                Log.d("PGB", "Error calling endpoint")
            }
        }
    }
}