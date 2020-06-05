package com.example.data.network.models

import com.example.data.database.models.TopicDbModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopicWebModel(
    var id: Int,
    var title: String,
    var description: String,
    @Json(name = "order_no")
    var orderNumber: Int,
    @Json(name = "course")
    var courseId: Int
)

fun TopicWebModel.asDbModel() = TopicDbModel(id, title, description, orderNumber, courseId)