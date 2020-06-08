package com.example.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.GlossaryWord

@Entity(tableName = "glossary")
data class GlossaryWordDbModel(
    @PrimaryKey
    var id: Int,
    var word: String,
    var translation: String,
    var definition: String,
    var lessonId: Int?,
    var userId: Int?
)

fun GlossaryWordDbModel.asDomainModel() =
    GlossaryWord(id, word, translation, definition, lessonId, userId)

fun GlossaryWord.asDbModel() =
    GlossaryWordDbModel(id, word, translation, definition, lessonId, userId)