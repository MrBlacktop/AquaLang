package com.example.data.network.models

import com.example.data.database.models.LessonDbModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LessonWebModel(
    var id: Int,
    var title: String,
    var content: String,
    @Json(name = "order_no")
    var orderNumber: Int,
    @Json(name = "topic")
    var topicId: Int,
    @Json(name = "exercise_count")
    var exerciseCount: Int
)

fun LessonWebModel.asDbModel() =
    LessonDbModel(id, title, content, orderNumber, topicId, exerciseCount)