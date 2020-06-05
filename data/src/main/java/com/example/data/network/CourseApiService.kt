package com.example.data.network

import com.example.data.network.models.CourseWebModel
import com.example.data.network.models.SubscriptionRespondWebModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CourseApiService {
    @GET("courses/")
    fun getAllCoursesAsync(): Deferred<List<CourseWebModel>>

    @GET("courses/index")
    fun getCourse(index: Int)


    @GET("courses/{id}/subscribe/")
    fun subscribeAsync(@Path("id") courseId: Int, @Header("Authorization") token: String): Deferred<SubscriptionRespondWebModel>

    @GET("courses/{id}/unsubscribe/")
    fun unsubscribeAsync(@Path("id") courseId: Int, @Header("Authorization") token: String): Deferred<SubscriptionRespondWebModel>
}

