package com.example.k2026_03_09_radio.ViewModels


import kotlinx.serialization.Serializable

@Serializable
data class RadioStation (val stationuuid: String?, val name: String?, val url: String?,
                         val url_resolved: String?, val favicon: String?, val country: String?,
                         val state: String?, val language: String?)
