package com.example.domain.models

data class Course(
    val id: Int,
    var title: String,
    var description: String,
    var authorName: String,
    var authorId: Int
)