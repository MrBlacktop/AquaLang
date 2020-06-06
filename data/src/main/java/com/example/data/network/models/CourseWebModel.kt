package com.example.data.network.models

import com.example.data.database.models.CourseDbModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseWebModel(
    val id: Int,
    var title: String,
    var description: String,
    @Json(name = "author_username")
    var authorName: String,
    @Json(name = "author")
    var authorId: Int,
    @Json(name = "picture")
    var pictureUrl: String?
)

fun CourseWebModel.asDatabaseModel() = CourseDbModel(id, title, description, authorName, authorId, false,pictureUrl)
