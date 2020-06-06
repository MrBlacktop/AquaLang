package com.example.data.database

import androidx.room.*
import com.example.data.database.models.CourseDbModel
import com.example.data.database.models.LessonDbModel
import com.example.data.database.models.TopicDbModel
import com.example.data.database.models.UserDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AquaLangDatabaseDao {
    @Insert
    fun insert(userDbModel: UserDbModel)

    @Query("delete  from user")
    fun clearUsers()

    @Query("select * from user limit 1 ")
    fun getUser(): UserDbModel?

    @Query("select * from courses")
    fun getAllCourses(): Flow<List<CourseDbModel>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCourses(courses: List<CourseDbModel>)

    @Query("select * from courses where id = :id")
    fun getCourse(id: Int): CourseDbModel

    @Update
    fun updateCourse(course: CourseDbModel)

    @Query("select * from topics where courseId =:courseId order by orderNumber")
    fun getCourseTopics(courseId: Int): Flow<List<TopicDbModel>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopics(topics: List<TopicDbModel>)

    @Query("select * from lessons where topicId =:topicId")
    fun getAllTopicLessons(topicId: Int): Flow<List<LessonDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLessons(lessons: List<LessonDbModel>)

    @Query("select * from lessons where id =:lessonId")
    fun getLesson(lessonId: Int): LessonDbModel?
}