package com.example.data.network.apiServices

import com.example.data.network.models.exercise.ExerciseWebModel
import com.example.data.network.models.exercise.SubmittedAnswerWebModel
import com.example.data.network.models.user.RegistrationRespondWebModel
import com.example.domain.models.exercise.SubmittedAnswer
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface ExerciseApiService {
    @GET("lessons/{id}/get_exercises/")
    fun getLessonExercisesAsync(
        @Path("id") lessonId: Int,
        @Header("Authorization") token: String
    ): Deferred<List<ExerciseWebModel>>

    @POST("answerhistory/")
    fun postExercisesResult(
        @Header("Authorization") token: String,
        @Body answers: List<SubmittedAnswerWebModel>
    ): Deferred<RegistrationRespondWebModel>
}