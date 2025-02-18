package com.example.k2025_02_15_viewmodelsavestatehandle

import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MyViewModelSaveHandle(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var myCount = savedStateHandle.get<Int>("myCount") ?: 0

    fun getMyCount() : Int {
        return myCount
    }

    fun incrementMyCount() {
        myCount++
        savedStateHandle["myCount"] = myCount
    }

    override fun onCleared() {
        super.onCleared()
        savedStateHandle["myCount"] = myCount
    }
}