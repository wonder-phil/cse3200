package models

class Score {

    fun incrementTotalQuestions() {
        totalQuestions++
    }

    fun updateScore(newScore: Double) {
        score = newScore
    }

    fun incrementScore(scoreChange: Double) {
        incrementTotalQuestions()
        score += scoreChange
    }

    fun getScore() : Double {
        return score
    }

    companion object {
        private var score = 0.0
        private var totalQuestions = 0
    }

}