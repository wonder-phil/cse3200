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
            //.baseUrl("https://api.hiro.so/extended/v1/block/0x52608bb89eb6d56e6a9ae79aab1c6a6c65c5ce41b9fea714f4609e0b04d5a4c0")
            .baseUrl("http://10.0.2.2:3003/bitcoin/")
            .build()
        Log.i("PGB", "Endpoint built")
        bitcoinRestInterface = retrofit.create(BitcoinApiService::class.java)
    }

    fun getOneBitcoin() {
        viewModelScope.launch {
            try {
                val bitcoin: Bitcoin = bitcoinRestInterface.getBitcoin()
                Log.i("PGB", "PGB my Bitcoin 2: " + bitcoin.name)
            } catch(e: Exception) {
                e.printStackTrace()
                Log.d("PGB", "calling endpoint")
            }
        }
    }
}