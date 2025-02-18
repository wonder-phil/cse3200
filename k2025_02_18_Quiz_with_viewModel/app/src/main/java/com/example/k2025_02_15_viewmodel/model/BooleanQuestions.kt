package com.example.k2025_02_15_viewmodel.model

enum class QuestionType {
    ARITHMETIC,
    SPACE,
    QUANTUM
}

data class BooleanQuestion(val id: Int, val text: String, val isTrue: Boolean, val questionType: QuestionType)

val booleanQuestionsList = listOf<BooleanQuestion> (
    BooleanQuestion(1,"2+2 = 7", false, QuestionType.ARITHMETIC),
    BooleanQuestion(2, "Pluto is a planet", false,QuestionType.SPACE),
    BooleanQuestion(3,"Saturn has only two moons", false, QuestionType.SPACE),
    BooleanQuestion(4,"Saturn has at least 146 moons", true, QuestionType.SPACE),
    BooleanQuestion(5,"Saturn has rings", true, QuestionType.SPACE),
    BooleanQuestion(6,"Saturn is the largest planet", false,QuestionType.SPACE),
    BooleanQuestion(7,"Jupiter is the largest planet", true, QuestionType.SPACE),
    BooleanQuestion(8,"The earth is the third planet from the sun", true, QuestionType.SPACE),
    BooleanQuestion(9,"The earth is between venus and mars", true, QuestionType.SPACE),
    BooleanQuestion(10,"The closest planet to the sun is mercury", true, QuestionType.SPACE),
    BooleanQuestion(11,"There are at least eight planets in our solar system", true, QuestionType.SPACE),
    BooleanQuestion(12,"There may be a planet X in our solar system", true, QuestionType.SPACE),
    BooleanQuestion(13,"In our solar system, neptune is the farthest planet from our sun", false, QuestionType.SPACE),
    BooleanQuestion(14,"In our solar system, uranus is the farthest planet from our sun", false, QuestionType.SPACE),
    BooleanQuestion(15,"Pluto is a dwarf planet", true, QuestionType.SPACE),
    BooleanQuestion(16,"Quantum computing leverages superposition and entanglement", true, QuestionType.QUANTUM),
    BooleanQuestion(17,"A qubit is a quantum-bit that can take many states", true, QuestionType.QUANTUM),
    BooleanQuestion(18,"A qubit can get Quantum decoherence and introduce errors", true, QuestionType.QUANTUM),
)
