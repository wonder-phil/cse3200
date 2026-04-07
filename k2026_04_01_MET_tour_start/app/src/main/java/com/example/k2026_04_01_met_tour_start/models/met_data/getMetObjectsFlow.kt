package com.example.k2026_04_01_met_tour_start.models.met_data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json

fun getMetObjectsFlow(numberOfObjects: Int): Flow<List<MetObject>> = flow<List<MetObject>> {

    val jsonString: String = httpGet( "curl.exe https://collectionapi.metmuseum.org/public/collection/v1/objects?departmentIds=7")
    val parser = Json { ignoreUnknownKeys = true }

    val MetObjects: List<MetObject> = parser.decodeFromString(jsonString)

    val first =MetObjects[0].toString()
    Log.i("PGB", "First = ${first.toString()}")

    emit(MetObjects)
}.flowOn(Dispatchers.IO)
