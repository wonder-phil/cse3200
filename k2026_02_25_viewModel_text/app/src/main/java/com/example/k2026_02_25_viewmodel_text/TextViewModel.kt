package com.example.k2026_02_25_viewmodel_text

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextViewModel : ViewModel() {
    private val _text = MutableLiveData("Hello ViewModel!")
    val text: LiveData<String> = _text

    fun setText(newText: String) {
        _text.value = newText
    }
}

