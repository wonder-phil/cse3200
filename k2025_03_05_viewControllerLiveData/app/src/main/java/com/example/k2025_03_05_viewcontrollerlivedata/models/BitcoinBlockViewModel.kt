package com.example.k2025_03_05_viewcontrollerlivedata.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.k2025_03_05_viewcontrollerlivedata.services.BitcoinApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BitcoinBlockViewModel : ViewModel() {
    private lateinit var bitcoinRestInterface: BitcoinApiService


    init {

        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.0.2.2:3003/bitstream/" ) //"https://blockstream.info/api/block/00000000000000000001f87c8c834f1528415dd07509bd93f9203b0288e88ba8"
            .build()
        //
        Log.i("PGB", "Endpoint built")
        bitcoinRestInterface = retrofit.create(BitcoinApiService::class.java)
    }

    /* */
    fun getOneBitcoin() {
        viewModelScope.launch {
            try {
                val bitcoin: BitcoinBlock = bitcoinRestInterface.getBitcoin()
                Log.i("PGB", "My Bitcoin 2: " + bitcoin.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("PGB", "Error calling endpoint")
            }
        }
    }
}
