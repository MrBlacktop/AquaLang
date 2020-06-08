package com.example.data.network.models

import com.example.data.database.models.GlossaryWordDbModel
import com.example.domain.models.GlossaryWord
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GlossaryWordWebModel(
    var id: Int,
    @Json(name = "foreign")
    var word: String,
    var translation: String,
    var definition: String,
    @Json(name = "lesson")
    var lessonId: Int?,
    @Json(name = "user")
    var userId: Int?
)

fun GlossaryWord.asWebModel() =
    GlossaryWordWebModel(id, word, translation, definition, lessonId, userId)

fun GlossaryWordWebModel.asDbModel() =
    GlossaryWordDbModel(id, word, translation, definition, lessonId, userId)