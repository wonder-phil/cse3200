package models

class Score {

    fun incrementTotalQuestionsAnswered() {
        totalQuestions++
    }

    fun updateScore(newScore: Double) {
        score = newScore
    }

    fun incrementScore(scoreChange: Double) {
        incrementTotalQuestionsAnswered()
        score += scoreChange
    }

    fun getScore() : Double {
        return score
    }

    fun getTotalQuestions() : Int {
        return totalQuestions
    }

    companion object {
        private var score = 0.0
        private var totalQuestions = 0
    }

}