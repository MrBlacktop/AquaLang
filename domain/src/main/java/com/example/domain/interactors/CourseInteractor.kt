package com.example.domain.interactors

import com.example.domain.models.Course
import com.example.domain.repositories.CourseRepository
import kotlinx.coroutines.flow.Flow


class CourseInteractor(private val courseRepository: CourseRepository) {
    fun loadCourses(): Flow<List<Course>> {
        return courseRepository.getAllCourses()
    }

    suspend fun sync(){
        courseRepository.synchronize()
    }

    fun getCourse(id: Int): Course{
        return courseRepository.getCourse(id)
    }
}