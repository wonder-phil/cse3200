package com.example.k2025_04_15_radioservicewviewmodel.models


class RadioStationsManager {
    private val allRadioStations = listOf(
        RadioStation("http://stream.whus.org:8000/whusfm", "UConn 0"),
        RadioStation("https://radio.canstream.co.uk:8083/live.mp3","Bolton FM 1"),
        RadioStation("http://stream.whus.org:8000/whusfm", "UConn 2"),
        RadioStation("https://radio.canstream.co.uk:8083/live.mp3","Bolton FM 3")
        /* RadioStation("http://stream.whus.org:8000/whusfm", "UConn 4"),
         RadioStation("https://radio.canstream.co.uk:8083/live.mp3","Bolton FM 5"),
         RadioStation("http://stream.whus.org:8000/whusfm", "UConn 6"),
         RadioStation("https://radio.canstream.co.uk:8083/live.mp3","Bolton FM 7")

         */
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
