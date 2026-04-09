package com.example.k2026_04_01_met_tour_start.models.met_data

import android.util.Log
import androidx.collection.emptyObjectList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json



class MetObjectsViewModel() : ViewModel() {
    private val _metObjects = MutableStateFlow<List<Int>>( emptyList<Int>())

    val metObjects: StateFlow<List<Int>> = _metObjects

    init {
        loadMetObjects()
    }

    private fun loadMetObjects() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val jsonString = httpGet(
                    "https://collectionapi.metmuseum.org/public/collection/v1/objects?departmentIds=7"
                )

                val parser = Json { ignoreUnknownKeys = true }
                val response = parser.decodeFromString<MetListOfObjects>(jsonString)

                _metObjects.value = response.objectIDs
            } catch (e: Exception) {
                Log.e("PGB", "Failed to load Met ObjectIDs", e)
            }
        }
    }
}