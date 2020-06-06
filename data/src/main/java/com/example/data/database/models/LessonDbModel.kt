package com.example.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.Lesson

@Entity(tableName = "lessons")
data class LessonDbModel(
    @PrimaryKey
    var id: Int,
    var title: String,
    var content: String,
    var orderNumber: Int,
    var topicId: Int,
    var exerciseCount: Int
)

fun LessonDbModel.asDomainModel() = Lesson(id, title, content, orderNumber, topicId, exerciseCount)