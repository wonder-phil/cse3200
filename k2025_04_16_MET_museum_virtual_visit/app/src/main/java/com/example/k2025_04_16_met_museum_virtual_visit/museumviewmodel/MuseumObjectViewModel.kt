package com.example.k2025_04_16_met_museum_virtual_visit.museumviewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.k2025_04_16_met_museum_virtual_visit.models.MuseumObject
import com.example.k2025_04_16_met_museum_virtual_visit.models.objectIds
import com.example.k2025_04_16_met_museum_virtual_visit.services.MuseumObjectService
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MuseumObjectViewModel : ViewModel() {

    private val _museumObjects = MutableLiveData<List<MuseumObject>>()
    val museumObjects: LiveData<List<MuseumObject>> = _museumObjects

    private val _isFetchingNewMuseumObjects = MutableLiveData<Boolean>(true)
    val isFetchingNewMuseumObjects: LiveData<Boolean> = _isFetchingNewMuseumObjects

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://collectionapi.metmuseum.org/")
        .build()

    private val museumObjectService = retrofit.create(MuseumObjectService::class.java)

    init {
        getTopMuseumObjects(1)
    }

    fun getTopMuseumObjects(page: Int) {
        val offset = (page - 1) * 10
        val limit = 10

        viewModelScope.launch {
            _isFetchingNewMuseumObjects.postValue(true)
            try {
                val museumObjects = getTopMuseumObjectsPaginated(offset, limit)
                val updatedStations = _museumObjects.value.orEmpty() + museumObjects
                _museumObjects.postValue(updatedStations)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            _isFetchingNewMuseumObjects.postValue(false)
        }
    }

    private suspend fun getTopMuseumObjectsPaginated(offset: Int, limit: Int): List<MuseumObject> =
        coroutineScope {

            val result = mutableListOf<MuseumObject>()
            var currentOffset = offset

            while (result.size < limit) {
                val deferredResults = (currentOffset until currentOffset + limit).map { i ->
                    async {
                        val response = museumObjectService.getMuseumObject(objectIds[i])
                        if (response.isSuccessful) {
                            response.body()
                                ?: throw Exception("Empty response body for ID: ${objectIds[i]}")
                        } else {
                            throw Exception("Failed to get object with ID: ${objectIds[i]}")
                        }
                    }
                }

                val fetchedObjects = deferredResults.map { it.await() }
                result.addAll(fetchedObjects.filter { it.primaryImageSmall.isNotEmpty() })

                currentOffset += limit
                if (currentOffset >= objectIds.size) break
            }

            result.take(limit)
        }
}