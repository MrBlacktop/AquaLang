package com.example.data.network.models.exercise

import com.example.domain.models.exercise.Exercise
import com.example.domain.models.exercise.ExerciseType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExerciseWebModel(
    var id: Int,
    var title: String,
    var content: String,
    var type: Int,
    @Json(name = "lesson")
    var lessonId: Int,
    @Json(name = "possible_answers")
    var answers: List<AnswerWebModel>
)

fun ExerciseWebModel.asDomainModel() =
    Exercise(
        id,
        title,
        content,
        ExerciseType.values()[type],
        lessonId,
        answers.map { it.asDomainModel() })