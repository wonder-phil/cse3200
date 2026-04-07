package com.example.k2026_04_01_met_tour_start.models.met_data


import kotlinx.serialization.Serializable

@Serializable
data class MetObject (val objectID: Int, val primaryImage: String, val primaryImageSmall: String, val title: Int,
                      val culture: String, val country: String)
