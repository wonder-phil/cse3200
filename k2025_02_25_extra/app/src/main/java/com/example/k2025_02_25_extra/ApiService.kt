package com.example.k2025_02_25_extra

import com.example.k2025_02_25_extra.models.User
import retrofit2.http.GET

interface ApiService {
    @GET("User")
    suspend fun getUser(): User
}
