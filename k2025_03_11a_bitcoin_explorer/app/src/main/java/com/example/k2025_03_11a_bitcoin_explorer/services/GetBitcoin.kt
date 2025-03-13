package com.example.k2025_03_11a_bitcoin_explorer.services

import com.example.k2025_03_11a_bitcoin_explorer.models.BitcoinContainer
import retrofit2.http.GET


interface GetBitcoin {
    @GET("BitcoinContainer")
    suspend fun getBitcoin() : BitcoinContainer
}