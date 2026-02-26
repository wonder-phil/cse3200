package com.example.k2025_02_16_viewmodel.viewModels

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class SimpleViewModel : ViewModel() {

    private var _myText = MutableLiveData("Hello ViewModel!")
    var myText: LiveData<String> = _myText

    private val _index = MutableLiveData(0)
    val index: LiveData<Int> = _index

    fun incrementIndex() {
        val newValue = (_index.value ?: 0) + 1
        _index.value = newValue
        println("_index ${_index.value}")
    }

    fun updateText(newText: String) {
        _myText.value = newText
    }
}

