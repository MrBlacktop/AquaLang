package com.example.domain.repositories

import com.example.domain.models.exercise.Exercise
import com.example.domain.models.exercise.SubmittedAnswer

interface ExerciseRepository {
    suspend fun getLessonExercises(lessonId: Int, token: String): List<Exercise>

    suspend fun sendExercisesResult(answers: List<SubmittedAnswer>, token: String)
}