package com.example.k2025_04_22_met_museum.models

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import retrofit2.http.GET

interface OneMetApiCall {
    @GET("DT1567.jpg")
    suspend fun getOneApiImage() : BitmapDrawable
}