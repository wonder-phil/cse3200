package com.example.k2025_04_16_met_museum_virtual_visit.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MuseumObject(
    val objectID: Int,
    val primaryImageSmall: String,

    val department: String,
    val accessionYear: String,

    val objectName: String,

    val artistDisplayName: String,
    val artistDisplayBio: String
) : Parcelable