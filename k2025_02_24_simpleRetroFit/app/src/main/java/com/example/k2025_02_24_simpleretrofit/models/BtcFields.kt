package com.example.k2025_02_24_simpleretrofit.models

data class BtcFields(
    val canonical: String,
    val height: String,
    val hash: String,
    val block_time: String,
    val block_time_iso: String,
    val tenure_height: String,
    val index_block_hash: String,
    val parent_block_hash: String,
    val burn_block_time: String,
    val burn_block_time_iso: String,
    val burn_block_hash: String,
    val burn_block_height: String,
    val miner_tx_id: String,
    val execution_cost_read_count: String,
    val execution_cost_read_length: String,
    val execution_cost_runtime: String,
    val execution_cost_write_count: String,
    val execution_cost_write_length: String,
    val txs: Array<String>
)