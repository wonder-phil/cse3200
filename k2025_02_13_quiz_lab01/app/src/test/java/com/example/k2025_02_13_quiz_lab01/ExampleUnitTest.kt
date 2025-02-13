package com.example.k2025_02_13_quiz_lab01

import com.example.k2025_02_13_quiz_lab01.model.ListOfBooleanQuestions
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    
    @Test
    fun initialQuestionIndexIsZero() {

        assertEquals(ListOfBooleanQuestions.getCurrentQuestionIndex(), 0)
    }

    @Test
    fun initialNextQuestionIsZero() {

        assertEquals(ListOfBooleanQuestions.nextQuestionIndex(), 0)
    }

    @Test
    fun initialGetCurrentQuestionIsZero() {

        ListOfBooleanQuestions.zero()

        for (i in 0..ListOfBooleanQuestions.getTotalQuestions()) {
            ListOfBooleanQuestions.nextQuestionIndex()
        }

        assertEquals(ListOfBooleanQuestions.nextQuestionIndex(), 0)
    }
}