package com.example.k2025_03_05_viewcontrollerlivedata.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BitcoinBlock(
    val id: String,
    val height: Int,
    val version: Int,
    val timestamp: Long,
    val tx_count: Int,
    val size: Int,
    val weight: Int,
    val merkle_root: String,
    val previousblockhash: String,
    val mediantime: Long,
    val nonce: Long,
    val bits: Long,
    val difficulty: Double
) : Parcelable