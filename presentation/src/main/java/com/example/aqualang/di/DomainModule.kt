package com.example.aqualang.di

import com.example.data.repositories.CourseRepositoryImpl
import com.example.data.repositories.UserRepositoryImpl
import com.example.domain.LoadCoursesUseCase
import com.example.domain.UserInteractor
import com.example.domain.repositories.CourseRepository
import com.example.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DomainModule {
    @Singleton
    @Provides
    fun providesUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository {
        return userRepositoryImpl
    }

    @Singleton
    @Provides
    fun providesUserInteractor(userRepository: UserRepository): UserInteractor {
        return UserInteractor(userRepository)
    }

    @Singleton
    @Provides
    fun providesCourseRepository(courseRepositoryImpl: CourseRepositoryImpl): CourseRepository {
        return courseRepositoryImpl
    }

    @Singleton
    @Provides
    fun providesLoadCoursesUseCase(courseRepository: CourseRepository): LoadCoursesUseCase {
        return LoadCoursesUseCase(courseRepository)
    }
}