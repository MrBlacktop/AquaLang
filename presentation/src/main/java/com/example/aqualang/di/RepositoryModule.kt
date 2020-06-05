package com.example.aqualang.di

import com.example.data.repositories.CourseRepositoryImpl
import com.example.data.repositories.TopicRepositoryImpl
import com.example.data.repositories.UserRepositoryImpl
import com.example.domain.repositories.CourseRepository
import com.example.domain.repositories.TopicRepository
import com.example.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun providesUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository {
        return userRepositoryImpl
    }

    @Singleton
    @Provides
    fun providesCourseRepository(courseRepositoryImpl: CourseRepositoryImpl): CourseRepository {
        return courseRepositoryImpl
    }

    @Singleton
    @Provides
    fun providesTopicRepository(topicRepositoryImpl: TopicRepositoryImpl): TopicRepository{
        return topicRepositoryImpl
    }
}