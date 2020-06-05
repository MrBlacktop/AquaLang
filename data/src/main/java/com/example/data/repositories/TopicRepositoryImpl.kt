package com.example.data.repositories

import com.example.data.database.AquaLangDatabaseDao
import com.example.data.database.models.TopicDbModel
import com.example.data.database.models.asDomainModel
import com.example.data.network.TopicApiService
import com.example.data.network.models.asDbModel
import com.example.domain.models.Topic
import com.example.domain.repositories.TopicRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(
    private val dao: AquaLangDatabaseDao,
    private val topicApiService: TopicApiService
) : TopicRepository {
    override fun getTopics(courseId: Int): Flow<List<Topic>> {
        return dao.getCourseTopics(courseId).map { it.map { entity -> entity.asDomainModel() } }
    }

    override suspend fun sync(courseId: Int) {
        withContext(Dispatchers.IO){

            val topics =
                topicApiService.getAllCourseTopicsAsync(courseId).await()
                    .map { it.asDbModel() }

            dao.insertTopics(topics)
        }
    }
}