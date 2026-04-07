package com.example.k2026_04_01_met_tour_start.models.met_data

import kotlinx.serialization.Serializable


@Serializable
data class MetListOfObjects(
    val total: Int,
    val objectIDs: List<Int>
)
