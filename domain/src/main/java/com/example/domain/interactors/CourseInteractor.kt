package com.example.domain.interactors

import com.example.domain.models.Course
import com.example.domain.repositories.CourseRepository
import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow


class CourseInteractor(private val courseRepository: CourseRepository, private val userRepository: UserRepository) {
    fun loadCourses(): Flow<List<Course>> {
        return courseRepository.getAllCourses()
    }

    suspend fun sync(){
        val user = userRepository.getUserFromDb() ?: throw Exception("current user not found")
        courseRepository.synchronize(user.id)
    }

    fun getCourse(id: Int): Course{
        return courseRepository.getCourse(id)
    }

    suspend fun subscribe(course: Course): Boolean{

        val token = userRepository.getUserToken()

        val result = courseRepository.subscribeUser(course.id, token)
        course.isActive = result

        courseRepository.updateCourse(course)

        return result

    }

    suspend fun unsubscribe(course: Course): Boolean{
        val token = userRepository.getUserToken()

        val result = courseRepository.unsubscribeUser(course.id, token)
        course.isActive = !result

        courseRepository.updateCourse(course)
        
        return result

    }

}