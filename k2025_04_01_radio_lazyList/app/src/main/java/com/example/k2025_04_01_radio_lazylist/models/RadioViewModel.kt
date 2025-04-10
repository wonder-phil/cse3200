package com.example.k2025_04_01_radio_lazylist.models

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.Messenger
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k2025_04_01_radio_lazylist.services.RadioService

class RadioViewModel(connection: ServiceConnection) : ViewModel() {

    override fun onCleared() {
        super.onCleared()

    }



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