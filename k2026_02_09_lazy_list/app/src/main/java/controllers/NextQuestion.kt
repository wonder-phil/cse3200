package controllers

import models.ListOfBooleanQuestions

class NextQuestion {

    private val totalQuestions = ListOfBooleanQuestions().getSize()

    companion object {
        private var currentQuestion = 0
    }

    fun getNextIncQuestionNumber() : Int {
        currentQuestion = (currentQuestion +1) % totalQuestions
        return currentQuestion
    }

    fun getNextRandomQuestionNumber() : Int {
        val getRand = (0..totalQuestions).random()
        return getRand
    }


}