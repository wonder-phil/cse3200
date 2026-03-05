package com.example.k2026_03_04_json_get

import androidx.lifecycle.ViewModel

class RadioStationViewModel: ViewModel() {
    val radioStationsFlow = getRadioStationFlow(3)


}