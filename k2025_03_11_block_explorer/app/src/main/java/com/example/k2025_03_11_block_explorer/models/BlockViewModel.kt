package com.example.k2025_03_11_block_explorer.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.k2025_03_11_block_explorer.services.BitcoinApiService
import com.example.k2025_03_11_block_explorer.services.BitcoinTipApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class BlockViewModel : ViewModel() {

    private lateinit var getBitcoinTipInterface : BitcoinTipApiService
    private lateinit var getBitcoinInterface : BitcoinApiService

    private val _currentBlockHash = MutableLiveData<String>("None yet")
    val currentBlockHash : LiveData<String> = _currentBlockHash

    private val _currentBitcoin = MutableLiveData<BitcoinBlock>(null)
    val currentBitcoin: LiveData<BitcoinBlock> = _currentBitcoin

    // local server: http://10.0.2.2:3003/bitstream/
    init {
        val retrofitInit = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl("https://blockstream.info/")
            .build()

        getBitcoinTipInterface = retrofitInit.create(BitcoinTipApiService::class.java)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://blockstream.info/")
            .build()

        getBitcoinInterface = retrofit.create(BitcoinApiService::class.java)
    }

    fun getThisBitcoin(bitcoinHash: String) {
        _currentBlockHash.value = bitcoinHash
        viewModelScope.launch {
            _currentBitcoin.value = getBitcoinInterface.getBitcoin(bitcoinHash)
            Log.i("PGB", "My Bitcoin 1: " + _currentBitcoin.value.toString())
        }
    }

    fun getPreviousBlock() {
        var previousBlockHash = currentBitcoin.value?.previousblockhash.toString()
        _currentBlockHash.value = previousBlockHash // currentBitcoin.value?.id.toString()
        viewModelScope.launch {
            _currentBitcoin.value = getBitcoinInterface.getBitcoin(previousBlockHash)
            Log.i("PGB", "My previous Bitcoin 2: " + _currentBitcoin.value.toString())
        }

    }

    fun getBitcoinChainTip() {
        var tipBtc: String = ""
        viewModelScope.launch {

            tipBtc = getBitcoinTipInterface.getTipBitcoin()
            Log.i("PGB", "My Bitcoin 3: " + tipBtc)
            getThisBitcoin(tipBtc)
        }
    }

}