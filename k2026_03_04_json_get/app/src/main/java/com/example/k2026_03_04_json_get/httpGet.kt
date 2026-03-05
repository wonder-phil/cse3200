package com.example.k2026_03_04_json_get

import java.net.URL

fun httpGet(url: String): String {
    val conn = URL(url).openConnection()
    conn.connect()

    return conn.getInputStream()
        .bufferedReader()
        .readText()
}