package com.example.data.network

import com.example.data.network.models.TopicWebModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface TopicApiService {
    @GET("courses/{id}/topics/")
    fun getAllCourseTopicsAsync(@Path("id") id:Int): Deferred<List<TopicWebModel>>
}