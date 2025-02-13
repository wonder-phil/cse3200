package com.example.k2025_02_13_quiz_lab01.model

class ListOfBooleanQuestions {

    companion object {

        val listOfBooleanQuestions = listOf(
            BooleanQuestion("Blue whales are the largest animals on Earth", answer = true),
            BooleanQuestion("The earth has no satellites", false),
            BooleanQuestion("Stamford is in Connecticut", true),
            BooleanQuestion("Hartford is the Capital of Connecticut", true),
            BooleanQuestion("Bugs are mammals", false),
            BooleanQuestion("Kotlin is a JVM-based language", true)
        )

        fun getBooleanQuestion(questionIndex: Int ) : BooleanQuestion {
            return listOfBooleanQuestions[questionIndex]
        }

        private var questionIndex: Int = 0

        fun getTotalQuestions() : Int {
            return listOfBooleanQuestions.size
        }

        fun getCurrentQuestionIndex() : Int {
            return questionIndex
        }

        fun zero() : Unit {
            questionIndex = 0
        }

        fun nextQuestionIndex(): Int {
            questionIndex = ++questionIndex % listOfBooleanQuestions.size

            return questionIndex
        }
    }
}