package com.example.data.repositories

import android.util.Log
import com.example.data.database.AquaLangDatabaseDao
import com.example.data.database.models.asDbModel
import com.example.data.database.models.asDomainModel
import com.example.data.network.apiServices.CourseApiService
import com.example.data.network.models.asDatabaseModel
import com.example.domain.models.Course
import com.example.domain.repositories.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val dao: AquaLangDatabaseDao,
    private val courseApiService: CourseApiService
) : CourseRepository {
    override fun getAllCourses(): Flow<List<Course>> {
        return dao.getAllCourses().map { it.map { entity -> entity.asDomainModel() } }
    }

    override suspend fun synchronize(userId: Int) {
        withContext(Dispatchers.IO) {
            val activeCourses = courseApiService.getActiveCoursesAsync(userId).await()
                .map { entity -> entity.asDatabaseModel() }

            val allCourses = courseApiService.getAllCoursesAsync().await()
            dao.insertAllCourses(allCourses.map { it.asDatabaseModel() })

            activeCourses.forEach { entity ->
                entity.isActive = true
                dao.updateCourse(entity)
            }
        }
    }

    override fun getCourse(id: Int): Course {
        Log.e("CourseRep", "id: $id")
        return dao.getCourse(id).asDomainModel()
    }

    override suspend fun subscribeUser(courseId: Int, token: String): Boolean {
        return courseApiService.subscribeAsync(courseId, token).await().status
    }

    override suspend fun unsubscribeUser(courseId: Int, token: String): Boolean {
        return courseApiService.unsubscribeAsync(courseId, token).await().status
    }

    override suspend fun updateCourse(course: Course) {
        withContext(Dispatchers.IO) {
            dao.updateCourse(course.asDbModel())
        }
    }
}