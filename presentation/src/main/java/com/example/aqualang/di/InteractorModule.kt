package com.example.aqualang.di

import com.example.data.repositories.CourseRepositoryImpl
import com.example.data.repositories.UserRepositoryImpl
import com.example.domain.interactors.CourseInteractor
import com.example.domain.interactors.TopicInteractor
import com.example.domain.interactors.UserInteractor
import com.example.domain.repositories.CourseRepository
import com.example.domain.repositories.TopicRepository
import com.example.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class InteractorModule {


    @Singleton
    @Provides
    fun providesUserInteractor(userRepository: UserRepository): UserInteractor {
        return UserInteractor(userRepository)
    }

    @Singleton
    @Provides
    fun providesCourseInteractor(
        courseRepository: CourseRepository,
        userRepository: UserRepository
    ): CourseInteractor {
        return CourseInteractor(courseRepository, userRepository)
    }

    @Singleton
    @Provides
    fun providesTopicInteractor(topicRepository: TopicRepository): TopicInteractor {
        return TopicInteractor(topicRepository)
    }
}