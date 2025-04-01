package com.example.k2025_04_01_radio_lazylist.models

class RadioStationsManager {
    private val allRadioStations = listOf(
        RadioStation("http://stream.whus.org:8000/whusfm", "UConn"),
        RadioStation("https://radio.canstream.co.uk:8083/live.mp3","Bolton FM"),
        RadioStation("http://stream.whus.org:8000/whusfm", "UConn"),
        RadioStation("https://radio.canstream.co.uk:8083/live.mp3","Bolton FM")
    )
    private var whichStation: Int = 0

    fun getStation() : RadioStation {
        return allRadioStations[whichStation]
    }

    fun getStation(whichStation: Int) : RadioStation {
        return allRadioStations[whichStation % allRadioStations.size]
    }

    fun nextStation() {
        whichStation = (whichStation + 1) % allRadioStations.size
    }

    fun getNumberOfRadioStations() : Int {
        return allRadioStations.size
    }

}
