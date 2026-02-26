package com.example.k2026_02_11_backstack_control.models

class Score {
    companion object {
        var totalScore: Int = 0

        fun startScore() {
            totalScore = 0
        }

        fun incrementTotalScore() {
            totalScore += 1
        }
    }
}