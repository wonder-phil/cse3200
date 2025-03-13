package com.example.k2025_03_11_block_explorer.services

import com.example.k2025_03_11_block_explorer.models.BitcoinBlock
import retrofit2.http.GET

interface BitcoinApiService {
    @GET("bitstream")
    suspend fun getBitcoin() : BitcoinBlock
}