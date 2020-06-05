package com.example.domain.repositories

import com.example.domain.models.Topic
import kotlinx.coroutines.flow.Flow

interface TopicRepository {
    fun getTopics(courseId: Int): Flow<List<Topic>>
    suspend fun sync(courseId: Int)
}