package com.example.domain.models

data class Lesson(
    var id: Int,
    var title: String,
    var content: String,
    var orderNumber: Int,
    var topicId: Int,
    var exerciseCount: Int
)