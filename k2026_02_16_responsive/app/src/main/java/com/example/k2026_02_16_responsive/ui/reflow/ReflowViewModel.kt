package com.example.k2026_02_16_responsive.ui.reflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReflowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is reflow Fragment"
    }
    val text: LiveData<String> = _text
}