package com.example.k2026_02_11_backstack_control.controllers


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.k2026_02_11_backstack_control.models.ListOfBooleanQuestions
import kotlin.reflect.KMutableProperty

enum class ComputeNextQuestion {
    LINEAR,
    UNIFORM_RANDOM
}

class NextQuestion(_nextQuestion: ComputeNextQuestion, capturedValue: MutableState<Int>) {

    init {
        nextQuestionCalculation = _nextQuestion
        capturedValue.value = currentQuestion.value
    }

    fun getQuestionIndex() : Int {
       when (nextQuestionCalculation) {
           ComputeNextQuestion.LINEAR -> { return getNextIncQuestionNumber() }
           ComputeNextQuestion.UNIFORM_RANDOM -> { return getNextRandomQuestionNumber() }
        }
    }
    fun getNextIncQuestionNumber() : Int {
        currentQuestion.value = (currentQuestion.value +1) % totalQuestions
        return currentQuestion.value
    }
    fun getNextRandomQuestionNumber() : Int {
        val getRand = (0..< totalQuestions).random()
        currentQuestion.value = getRand
        return currentQuestion.value
    }

    companion object {
        private val currentQuestion = mutableStateOf(0)
        private val totalQuestions = ListOfBooleanQuestions().getSize()
        private var nextQuestionCalculation: ComputeNextQuestion = ComputeNextQuestion.LINEAR
    }

}