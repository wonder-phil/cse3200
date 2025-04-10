package com.example.k2025_04_01_radio_lazylist.models

enum class RadioState(val intValue: Int) {
    START(1),
    PREPARE_ASYNC(2),
    STOP(3),
    DESTROY(4)
}