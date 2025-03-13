package com.example.k2025_03_11a_bitcoin_explorer.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.k2025_03_11a_bitcoin_explorer.services.GetBitcoin
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BitcoinViewModel: ViewModel() {

    private lateinit var getBitcoinInterface: GetBitcoin

    private var _bitcoinHash = MutableLiveData<String>("None yet")
    val bitcoinHash : LiveData<String> = _bitcoinHash

    init {
        var retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.0.2.2:3003/bitstream/")
            .build()

        getBitcoinInterface = retrofit.create(GetBitcoin::class.java)
    }

    fun getOneBitcoin() {
        viewModelScope.launch {
            val bitcoin = getBitcoinInterface.getBitcoin().id
            Log.i("PGB", "< ${bitcoin.toString()} >")
            _bitcoinHash.value = bitcoin.toString()
        }
    }


}