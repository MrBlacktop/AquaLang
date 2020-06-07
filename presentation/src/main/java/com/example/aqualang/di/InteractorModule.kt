package com.example.aqualang.di

import com.example.data.repositories.CourseRepositoryImpl
import com.example.data.repositories.UserRepositoryImpl
import com.example.domain.interactors.CourseInteractor
import com.example.domain.interactors.LessonInteractor
import com.example.domain.interactors.TopicInteractor
import com.example.domain.interactors.UserInteractor
import com.example.domain.repositories.*
import com.example.domain.useCases.LoadExercisesUseCase
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

    @Singleton
    @Provides
    fun providesLessonInteractor(lessonRepository: LessonRepository): LessonInteractor {
        return LessonInteractor(lessonRepository)
    }

    @Singleton
    @Provides
    fun providesLoadExercisesUseCase(
        exerciseRepository: ExerciseRepository,
        userRepository: UserRepository
    ): LoadExercisesUseCase {
        return LoadExercisesUseCase(exerciseRepository, userRepository)
    }
}