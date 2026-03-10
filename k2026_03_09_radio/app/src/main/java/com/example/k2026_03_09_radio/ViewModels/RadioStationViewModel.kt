package com.example.k2026_03_09_radio.ViewModels

import androidx.lifecycle.ViewModel

class RadioStationViewModel: ViewModel() {
    val radioStationsFlow = getRadioStationsFlow(100)


}