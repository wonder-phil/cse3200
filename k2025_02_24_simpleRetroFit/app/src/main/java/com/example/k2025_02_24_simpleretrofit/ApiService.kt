package com.example.k2025_02_24_simpleretrofit

import com.example.k2025_02_24_simpleretrofit.models.User
import retrofit2.http.GET

interface ApiService {
    @GET("User")
    suspend fun getUsers(): User
}