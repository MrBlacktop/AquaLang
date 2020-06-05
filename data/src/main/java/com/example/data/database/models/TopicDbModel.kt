package com.example.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.Topic

@Entity(tableName = "topics")
data class TopicDbModel(
    @PrimaryKey
    var id: Int,
    var title: String,
    var description: String,
    var orderNumber: Int,
    var courseId: Int
)

fun TopicDbModel.asDomainModel() = Topic(id, title, description, orderNumber, courseId)