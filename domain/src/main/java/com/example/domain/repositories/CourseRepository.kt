package com.example.domain.repositories

import com.example.domain.models.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun getAllCourses(): Flow<List<Course>>
    suspend fun synchronize()
    fun getCourse(id: Int): Course
}