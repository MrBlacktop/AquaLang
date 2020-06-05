package com.example.domain.interactors

import com.example.domain.models.Topic
import com.example.domain.repositories.TopicRepository
import kotlinx.coroutines.flow.Flow

class TopicInteractor(private val topicRepository: TopicRepository) {
    fun getTopics(courseId: Int): Flow<List<Topic>>{
        return topicRepository.getTopics(courseId)
    }

    suspend fun sync(courseId: Int){
        topicRepository.sync(courseId)
    }
}