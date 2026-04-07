package com.example.k2026_04_01_met_tour_start.models.met_data

import android.util.Log
import kotlinx.serialization.json.Json

fun getMetDepartment(): List<Department> {

    val jsonString: String =
        httpGet("https://collectionapi.metmuseum.org/public/collection/v1/departments")

    val parser = Json { ignoreUnknownKeys = true }

    val response: DepartmentsResponse =
        parser.decodeFromString(jsonString)

    val departments = response.departments

    val first = departments.firstOrNull()
    Log.i("PGB", "First = $first")

    return departments
}