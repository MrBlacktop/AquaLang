package com.example.data.repositories

import android.util.Log
import com.example.data.database.AquaLangDatabaseDao
import com.example.data.database.models.asDomainModel
import com.example.data.network.apiServices.LessonApiService
import com.example.data.network.models.asDbModel
import com.example.domain.models.Lesson
import com.example.domain.repositories.LessonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LessonRepositoryImpl @Inject constructor(
    private val dao: AquaLangDatabaseDao,
    private val lessonApiService: LessonApiService
) : LessonRepository {

    override fun getLessons(topicId: Int): Flow<List<Lesson>> {
        return dao.getAllTopicLessons(topicId).map { it.map { entity -> entity.asDomainModel() } }
    }

    override suspend fun sync(topicId: Int) {
        withContext(Dispatchers.IO) {
            val lessons =
                lessonApiService.getAllTopicLessonsAsync(topicId).await().map { it.asDbModel() }

            dao.insertLessons(lessons)
        }
    }

    override fun getLesson(lessonId: Int): Lesson {
        return dao.getLesson(lessonId)?.asDomainModel() ?: throw Exception("Lesson not found")
    }
}