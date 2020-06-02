package com.example.data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {
    @Singleton
    @Provides
    fun providesDatabase(): AquaLangDatabase =
        Room.databaseBuilder(context, AquaLangDatabase::class.java, "aqualang_database")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providesAquaLangDatabaseDao(aquaLangDatabase: AquaLangDatabase) =
        aquaLangDatabase.aquaLangDatabaseDao

}