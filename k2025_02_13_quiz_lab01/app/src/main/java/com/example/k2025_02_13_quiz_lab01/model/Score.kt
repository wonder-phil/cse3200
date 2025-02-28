package com.example.k2025_02_13_quiz_lab01.model

class Score {

    companion object {
        var totalCorrect: Int = 0
        var totalAnswered: Int = 0
        var totalSkipped: Int = 0

        fun incrementTotalSkipped() {
            totalSkipped = totalSkipped +1
        }

        fun incrementTotalCorrect() {
            totalCorrect = totalCorrect +1
        }
    }
}