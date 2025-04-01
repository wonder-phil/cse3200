package com.example.k2025_03_11_block_explorer.services

import com.example.k2025_03_11_block_explorer.models.BitcoinBlock
import retrofit2.http.GET
import retrofit2.http.Path

interface BitcoinApiService {
    @GET("api/block/{blockHash}")
    suspend fun getBitcoin(@Path("blockHash")  myHash: String) : BitcoinBlock
}