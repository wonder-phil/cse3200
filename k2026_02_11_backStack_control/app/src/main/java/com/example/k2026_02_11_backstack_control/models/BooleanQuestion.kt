package com.example.k2026_02_11_backstack_control.models


enum class QuestionType {
    SPACE,
    ARITHEMETIC
}

data class BooleanQuestion(val number: Int, val text:String,
                           val isTrue: Boolean,
                           val questionType: QuestionType)