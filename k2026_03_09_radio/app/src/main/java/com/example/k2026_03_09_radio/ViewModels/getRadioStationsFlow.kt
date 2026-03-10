package com.example.k2026_03_09_radio.ViewModels

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json
import kotlin.toString

fun getRadioStationsFlow(numberOfStations: Int): Flow<List<RadioStation>> = flow<List<RadioStation>> {

    val jsonString: String = httpGet( "https://de1.api.radio-browser.info/json/stations/search?limit=$numberOfStations&name=api")
    val parser = Json { ignoreUnknownKeys = true }

    val stations: List<RadioStation> = parser.decodeFromString(jsonString)

    val first = stations[0].toString()
    Log.i("PGB", "First = ${first.toString()}")

    emit(stations)
}.flowOn(Dispatchers.IO)