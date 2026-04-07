package com.example.k2026_04_07_simple_coroutine

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    init {
        viewModelScope.launch {
            delay(3000L)
            Log.i("PGB", "view model")
        }
    }

}