package models

enum class QuestionType {
    SPACE,
    ARITHEMETIC
}

data class BooleanQuestion(val number: Int, val text:String,
                           val isTrue: Boolean, val questionType: QuestionType)