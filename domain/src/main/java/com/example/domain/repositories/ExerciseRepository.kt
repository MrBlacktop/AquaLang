package com.example.domain.repositories

import com.example.domain.models.exercise.Exercise

interface ExerciseRepository {
    suspend fun getLessonExercises(lessonId: Int, token: String): List<Exercise>
}