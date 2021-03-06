package com.example.domain.repositories

import com.example.domain.models.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun getAllCourses(): Flow<List<Course>>
    suspend fun synchronize(userId: Int)
    fun getCourse(id: Int): Course

    suspend fun subscribeUser(courseId: Int, token:String): Boolean
    suspend fun unsubscribeUser(courseId: Int, token:String): Boolean

    suspend fun updateCourse(course: Course)

}