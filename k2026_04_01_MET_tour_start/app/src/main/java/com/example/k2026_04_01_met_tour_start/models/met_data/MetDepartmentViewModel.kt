package com.example.k2026_04_01_met_tour_start.models.met_data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

interface NumbersApi {
    suspend fun getNumbers(): List<Int>
}

class MetDepartmentViewModel : ViewModel() {

    private val _departments = MutableStateFlow<List<Department>>(emptyList())
    val departments: StateFlow<List<Department>> = _departments

    init {
        loadDepartments()
    }

    private fun loadDepartments() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val jsonString = httpGet(
                    "https://collectionapi.metmuseum.org/public/collection/v1/departments"
                )

                val parser = Json { ignoreUnknownKeys = true }
                val response = parser.decodeFromString<DepartmentsResponse>(jsonString)

                _departments.value = response.departments
            } catch (e: Exception) {
                Log.e("PGB", "Failed to load departments", e)
            }
        }
    }
}