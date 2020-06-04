package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.models.CourseDbModel
import com.example.data.database.models.UserDbModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.selects.select

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

}