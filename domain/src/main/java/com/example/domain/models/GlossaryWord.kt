package com.example.domain.models

data class GlossaryWord(
    var id: Int = 0,
    var word: String = "",
    var translation: String = "",
    var definition: String = "",
    var lessonId: Int? = null,
    var userId: Int? = null
)