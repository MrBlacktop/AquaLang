package com.example.data.repositories

import com.example.data.database.AquaLangDatabaseDao
import com.example.data.database.models.asDomainModel
import com.example.data.network.CourseApiService
import com.example.data.network.models.asDatabaseModel
import com.example.domain.models.Course
import com.example.domain.repositories.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(private val dao: AquaLangDatabaseDao, private val courseApiService: CourseApiService): CourseRepository {
    override fun getAllCourses(): Flow<List<Course>> {
        return dao.getAllCourses().map { it.map { entity -> entity.asDomainModel() } }
    }

    override suspend fun synchronize() {
        withContext(Dispatchers.IO){
            var courses = courseApiService.getAllCoursesAsync().await()
            dao.insertAllCourses(courses.map { it.asDatabaseModel() })
        }
    }
}