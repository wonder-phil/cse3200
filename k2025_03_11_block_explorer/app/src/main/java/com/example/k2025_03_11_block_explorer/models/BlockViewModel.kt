package com.example.k2025_03_11_block_explorer.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.k2025_03_11_block_explorer.services.BitcoinApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BlockViewModel : ViewModel() {

    private lateinit var getBitcoinInterface : BitcoinApiService

    private val _btcHash = MutableLiveData<String>("None yet")
    val btcHash : LiveData<String> = _btcHash

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.0.2.2:3003/bitstream/")
            .build()

        getBitcoinInterface = retrofit.create(BitcoinApiService::class.java)
    }

    fun getOneBitcoin() {
        viewModelScope.launch {
            val bitcoin: BitcoinBlock = getBitcoinInterface.getBitcoin()
            Log.i("PGB", "My Bitcoin 2: " + bitcoin.toString())
        }
    }

}