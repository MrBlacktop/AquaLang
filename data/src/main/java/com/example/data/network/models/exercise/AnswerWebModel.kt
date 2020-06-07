package com.example.data.network.models.exercise

import com.example.domain.models.exercise.Answer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnswerWebModel(
    var id:Int,
    @Json(name = "exercise")
    var exerciseId: Int,
    @Json(name = "value")
    var content: String,
    @Json(name = "correct")
    var isCorrect: Boolean
)

fun AnswerWebModel.asDomainModel() = Answer(id, exerciseId, content, isCorrect)