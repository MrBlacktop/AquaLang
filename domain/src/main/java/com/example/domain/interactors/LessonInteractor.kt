package com.example.domain.interactors

import com.example.domain.models.Lesson
import com.example.domain.repositories.LessonRepository
import kotlinx.coroutines.flow.Flow

class LessonInteractor(private val lessonRepository: LessonRepository) {

    fun getLessons(topicId: Int): Flow<List<Lesson>> {
        return lessonRepository.getLessons(topicId)
    }

    suspend fun sync(topicId: Int) {
        lessonRepository.sync(topicId)
    }

    fun getLesson(lessonId: Int): Lesson{
        return lessonRepository.getLesson(lessonId)
    }
}