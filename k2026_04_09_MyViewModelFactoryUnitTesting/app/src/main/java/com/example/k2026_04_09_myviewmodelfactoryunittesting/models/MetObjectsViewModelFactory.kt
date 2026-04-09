package com.example.k2026_04_09_myviewmodelfactoryunittesting.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MetObjectsViewModelFactory(
    private val getNumbers: suspend () -> List<Int>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MetObjectsViewModel::class.java)) {
            return MetObjectsViewModel(getNumbers) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}