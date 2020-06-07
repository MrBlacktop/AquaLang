package com.example.domain.models.exercise

data class Answer(
    var id:Int,
    var exerciseId: Int,
    var content: String,
    var isCorrect: Boolean
)