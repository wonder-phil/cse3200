package com.example.k2025_03_03_retrofit_in_viewmodel.ViewModels

import android.telecom.Call
import retrofit2.http.GET

interface BitcoinApiService {
    @GET("Bitcoin")
    suspend fun getBitcoin() : Bitcoin
}