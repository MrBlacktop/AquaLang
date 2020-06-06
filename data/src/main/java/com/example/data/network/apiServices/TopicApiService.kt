package com.example.data.network.apiServices

import com.example.data.network.models.TopicWebModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface TopicApiService {
    @GET("courses/{id}/get_topics/")
    fun getAllCourseTopicsAsync(@Path("id") id:Int): Deferred<List<TopicWebModel>>
}