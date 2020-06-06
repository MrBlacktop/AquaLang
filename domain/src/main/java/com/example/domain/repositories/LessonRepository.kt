package com.example.domain.repositories

import com.example.domain.models.Lesson
import kotlinx.coroutines.flow.Flow

interface LessonRepository {
    fun getLessons(topicId: Int): Flow<List<Lesson>>
    suspend fun sync(topicId: Int)

    fun getLesson(lessonId: Int): Lesson
}