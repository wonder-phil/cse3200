package com.example.k2025_02_20a_viewmodel.viewModels

import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private val _myText = MutableLiveData<String>("Hello world")
    val myText: LiveData<String> = _myText

    private val _myInt = MutableLiveData<Int>(0)
    val myInt : LiveData<Int> = _myInt

    fun updateText(newText: String) {
        _myText.value = newText
    }

    fun incrementMyInt() {
        _myInt.value = _myInt?.value?.plus(1)
    }

}