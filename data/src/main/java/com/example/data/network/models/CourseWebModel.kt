package com.example.data.network.models

import com.example.data.database.models.CourseDbModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseWebModel(
    val id: Int,
    var title: String,
    var description: String,
    var authorName: String
)

fun CourseWebModel.asDatabaseModel() = CourseDbModel(id, title, description, authorName)