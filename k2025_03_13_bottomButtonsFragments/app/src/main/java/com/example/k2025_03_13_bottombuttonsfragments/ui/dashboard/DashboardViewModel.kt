package com.example.k2025_03_13_bottombuttonsfragments.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    var text: LiveData<String> = _text

    fun setText() {
        _text.value = "Hello!"
    }
}