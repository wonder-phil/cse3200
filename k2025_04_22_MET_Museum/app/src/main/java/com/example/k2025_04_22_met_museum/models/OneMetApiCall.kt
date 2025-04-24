package com.example.k2025_04_22_met_museum.models

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface OneMetApiCall {
    @GET //("DT1567.jpg")
    suspend fun downloadImage(@Url url: String): Response<ResponseBody>
}