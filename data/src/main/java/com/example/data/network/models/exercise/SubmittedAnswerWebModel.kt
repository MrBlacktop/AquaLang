package com.example.data.network.models.exercise

import com.example.domain.models.exercise.SubmittedAnswer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubmittedAnswerWebModel (
    @Json(name = "exercise")
    var exerciseId:Int,
    @Json(name = "correct")
    var isCorrect: Boolean
)

fun SubmittedAnswer.asWebModel() = SubmittedAnswerWebModel(exerciseId,isCorrect)