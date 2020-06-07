package com.example.domain.models.exercise

data class Exercise(
    var id: Int,
    var title: String,
    var content: String,
    var type: ExerciseType,
    var lessonId: Int,
    var answers: List<Answer>
)