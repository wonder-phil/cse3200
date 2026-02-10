package com.example.k2026_02_09_lazy_list

import controllers.NextQuestion
import models.ListOfBooleanQuestions
import org.junit.Assert
import org.junit.Test

class NextQuestionTests {

    @Test
    fun getNextIncQuestionNumberTest_1() {
        val allQuestions = ListOfBooleanQuestions().getListOfBooleanQuestions()
        val currentQuestion0 = 0

        val nextQuestion = NextQuestion()

        Assert.assertEquals(nextQuestion.getNextIncQuestionNumber(), currentQuestion0 + 1)
    }

    @Test
    fun getNextIncQuestionNumberTest_2() {
        val allQuestions = ListOfBooleanQuestions().getListOfBooleanQuestions()
        val totalNumberOfQuestions = allQuestions.size
        val nextQuestion = NextQuestion()

        repeat(totalNumberOfQuestions-1, { nextQuestion.getNextIncQuestionNumber() })
        Assert.assertEquals(nextQuestion.getNextIncQuestionNumber(), 1)
    }


    @Test
    fun getNextRandomQuestionNumberTest_1() {
        val allQuestions = ListOfBooleanQuestions().getListOfBooleanQuestions()
        val totalNumberOfQuestions = allQuestions.size
        val nextQuestion = NextQuestion()

        Assert.assertTrue(nextQuestion.getNextRandomQuestionNumber() in (0..<totalNumberOfQuestions))
    }

}