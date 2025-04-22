package com.example.k2025_04_16_met_museum_virtual_visit.services


import com.example.k2025_04_16_met_museum_virtual_visit.models.MuseumObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MuseumObjectService {
    @GET("/public/collection/v1/objects/{id}")
    suspend fun getMuseumObject(
        @Path("id") id: Int
    ): Response<MuseumObject>
}