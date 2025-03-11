package com.example.k2025_03_05_viewcontrollerlivedata.services

import com.example.k2025_03_05_viewcontrollerlivedata.models.BitcoinBlock
import retrofit2.http.GET


interface BitcoinApiService {
    @GET("bitstream")
    suspend fun getBitcoin() : BitcoinBlock
}