package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.models.*

@Database(
    entities = [UserDbModel::class, CourseDbModel::class, TopicDbModel::class, LessonDbModel::class, GlossaryWordDbModel::class],
    version = 10,
    exportSchema = false
)
abstract class AquaLangDatabase : RoomDatabase() {
    abstract val aquaLangDatabaseDao: AquaLangDatabaseDao
}