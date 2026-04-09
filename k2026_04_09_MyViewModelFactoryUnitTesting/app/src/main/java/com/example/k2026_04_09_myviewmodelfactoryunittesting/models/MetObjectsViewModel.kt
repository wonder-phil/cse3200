package com.example.k2026_04_09_myviewmodelfactoryunittesting.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MetObjectsViewModel(
    private val getNumbers: suspend () -> List<Int>
) : ViewModel() {

    private val _metObjects = MutableStateFlow<List<Int>>(emptyList())
    val metObjects: StateFlow<List<Int>> = _metObjects.asStateFlow()

    init {
        loadMetObjects()
    }

    private fun loadMetObjects() {
        viewModelScope.launch {
            _metObjects.value = getNumbers()
        }
    }
}