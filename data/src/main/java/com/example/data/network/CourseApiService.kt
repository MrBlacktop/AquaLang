package com.example.data.network

import com.example.data.network.models.CourseWebModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CourseApiService {
    @GET("")
    fun getAllCoursesAsync(): Deferred<List<CourseWebModel>>
}