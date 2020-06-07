package com.example.data.network.apiServices

import com.example.data.network.models.exercise.ExerciseWebModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ExerciseApiService {
    @GET("lessons/{id}/get_exercises/")
    fun getLessonExercisesAsync(
        @Path("id") lessonId: Int,
        @Header("Authorization") token: String
    ): Deferred<List<ExerciseWebModel>>
}