package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.models.CourseDbModel
import com.example.data.database.models.TopicDbModel
import com.example.data.database.models.UserDbModel

@Database(entities = [UserDbModel::class, CourseDbModel::class, TopicDbModel::class], version = 5)
abstract class AquaLangDatabase: RoomDatabase() {
    abstract val aquaLangDatabaseDao: AquaLangDatabaseDao
}