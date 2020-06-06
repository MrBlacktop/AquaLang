package com.example.data.network.apiServices

import com.example.data.network.models.CourseWebModel
import com.example.data.network.models.SubscriptionRespondWebModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CourseApiService {
    @GET("courses/")
    fun getAllCoursesAsync(): Deferred<List<CourseWebModel>>

    @GET("courses/{id}/subscribe/")
    fun subscribeAsync(@Path("id") courseId: Int, @Header("Authorization") token: String): Deferred<SubscriptionRespondWebModel>

    @GET("courses/{id}/unsubscribe/")
    fun unsubscribeAsync(@Path("id") courseId: Int, @Header("Authorization") token: String): Deferred<SubscriptionRespondWebModel>

    @GET("users/{id}/subs/")
    fun getActiveCoursesAsync(@Path("id") userId: Int): Deferred<List<CourseWebModel>>
}

