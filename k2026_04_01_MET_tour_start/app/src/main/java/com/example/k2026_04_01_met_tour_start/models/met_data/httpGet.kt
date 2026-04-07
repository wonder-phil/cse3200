package com.example.k2026_04_01_met_tour_start.models.met_data


import java.net.URL

fun httpGet(url: String): String {
    val conn = URL(url).openConnection()
    conn.connect()

    return conn.getInputStream()
        .bufferedReader()
        .readText()
}