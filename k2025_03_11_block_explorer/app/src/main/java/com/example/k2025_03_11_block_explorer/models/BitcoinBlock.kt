package com.example.k2025_03_11_block_explorer.models

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
    val mediantime: Int,
    val nonce: Long,
    val bits: Long,
    val difficulty: Double
) : Parcelable