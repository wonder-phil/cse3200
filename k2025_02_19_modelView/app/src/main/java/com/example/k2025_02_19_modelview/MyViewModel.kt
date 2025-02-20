package com.example.k2025_02_19_modelview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// from Building a Jetpack Compose Viewwith ViewModel by Rafael Meneghelo, Medium 2023

class MyViewModel : ViewModel() {
    private val _text = MutableLiveData("Hello, World!")
    val text: LiveData<String> = _text

    fun updateText(newText: String) {
        _text.value = newText
    }
}