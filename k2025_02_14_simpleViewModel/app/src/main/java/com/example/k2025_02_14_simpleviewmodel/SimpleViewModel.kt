package com.example.k2025_02_14_simpleviewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class SimpleViewModel : ViewModel() {

    var myText by mutableStateOf("Hello ViewModel !")

}
