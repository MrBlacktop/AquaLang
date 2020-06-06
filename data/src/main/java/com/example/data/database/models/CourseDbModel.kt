package com.example.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.Course

@Entity(tableName = "courses")
data class CourseDbModel(
    @PrimaryKey
    val id: Int,
    var title: String,
    var description: String,
    var authorName: String,
    var authorId: Int,
    var isActive: Boolean,
    var pictureUrl: String?
)

fun CourseDbModel.asDomainModel() =
    Course(id, title, description, authorName, authorId, isActive,pictureUrl)

fun Course.asDbModel() = CourseDbModel(id, title, description, authorName, authorId, isActive,pictureUrl)

