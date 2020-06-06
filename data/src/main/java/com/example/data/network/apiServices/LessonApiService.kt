package com.example.data.network.apiServices

import com.example.data.network.models.LessonWebModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface LessonApiService {
    @GET("topics/{id}/get_lessons/")
    fun getAllTopicLessonsAsync(@Path("id") topicId: Int): Deferred<List<LessonWebModel>>
}