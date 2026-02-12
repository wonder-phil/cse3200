package com.example.k2026_02_11_backstack_control.models


class ListOfBooleanQuestions {

    fun getListOfBooleanQuestions() : List<BooleanQuestion> {
        return listOfBooleanQuestions
    }

    public fun getSize() : Int {
        return listOfBooleanQuestions.size
    }

    companion object {
        var listOfBooleanQuestions: List<BooleanQuestion> = listOf<BooleanQuestion>(

            BooleanQuestion(1, "Our solar system has 13 planets", false, QuestionType.SPACE),
            BooleanQuestion(2, "Our solar system has 8 planets", true, QuestionType.SPACE),
            BooleanQuestion(
                3,
                "Our solar system has at least 2 dwarf planets",
                true,
                QuestionType.SPACE
            ),
            BooleanQuestion(
                4,
                "Jupiter is the largest planet in our solar system",
                true,
                QuestionType.SPACE
            ),
            BooleanQuestion(5, "Our solar system is in the Milky Way", true, QuestionType.SPACE),
            BooleanQuestion(6, "Mars is the 3rd planet from the Sun", false, QuestionType.SPACE),
            BooleanQuestion(7, "Saturn has rings", false, QuestionType.SPACE),
            BooleanQuestion(
                8,
                "Neptune is the farthest planet from the Sun",
                true,
                QuestionType.SPACE
            ),
            BooleanQuestion(9, "The Earth has two moons", false, QuestionType.SPACE),
            BooleanQuestion(10, "The Moon has no atmosphere", false, QuestionType.SPACE),
            BooleanQuestion(
                11,
                "Mars is sometimes called the red planet",
                true,
                QuestionType.SPACE
            ),
            BooleanQuestion(12, "Kuiper Belt includes dwarf planets", true, QuestionType.SPACE)
        )
    }

}