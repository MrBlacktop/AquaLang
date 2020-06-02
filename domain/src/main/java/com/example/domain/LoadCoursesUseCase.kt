package com.example.domain

import com.example.domain.models.Course
import com.example.domain.repositories.CourseRepository
import kotlinx.coroutines.flow.Flow


class LoadCoursesUseCase(private val courseRepository: CourseRepository) {
    suspend fun loadCourses(): Flow<List<Course>> {
        courseRepository.synchronize()
        return courseRepository.getAllCourses()
    }
}