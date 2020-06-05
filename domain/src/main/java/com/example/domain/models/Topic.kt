package com.example.domain.models

data class Topic(
    var id: Int,
    var title: String,
    var description: String,
    var orderNumber: Int,
    var courseId: Int
)