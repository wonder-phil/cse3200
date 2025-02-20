package com.example.k2024_02_18_simpleviewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MyViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var myCount: Int = savedStateHandle["myCount"] ?: 0

    fun incrementCount() {
        myCount++
        savedStateHandle["myCount"] = myCount
    }

    fun getMyCount() : Int {
        return myCount
    }
}

/*

    private var myCount: Int = savedStateHandle["myCount"] ?: 0

    fun incrementCount() {
        myCount++
        savedStateHandle["myCount"] = myCount
    }

    fun getMyCount() : Int {
        return myCount
    }
 */