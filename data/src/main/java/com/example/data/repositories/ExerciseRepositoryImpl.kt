package com.example.data.repositories

import android.util.Log
import com.example.data.network.apiServices.ExerciseApiService
import com.example.data.network.models.exercise.asDomainModel
import com.example.domain.models.exercise.Exercise
import com.example.domain.repositories.ExerciseRepository
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(private val exerciseApiService: ExerciseApiService) :
    ExerciseRepository {
    override suspend fun getLessonExercises(lessonId: Int, token: String): List<Exercise> {
        val exercises = exerciseApiService.getLessonExercisesAsync(lessonId, token)
            .await()
            .map { it.asDomainModel() }
        Log.i("ExerciseRepositoryImpl",exercises.toString())
        return exercises
    }

}