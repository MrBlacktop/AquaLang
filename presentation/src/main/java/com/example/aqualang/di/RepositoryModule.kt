package com.example.aqualang.di

import com.example.data.repositories.*
import com.example.domain.repositories.*
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
    fun providesTopicRepository(topicRepositoryImpl: TopicRepositoryImpl): TopicRepository {
        return topicRepositoryImpl
    }

    @Singleton
    @Provides
    fun providesLessonRepository(lessonRepositoryImpl: LessonRepositoryImpl): LessonRepository {
        return lessonRepositoryImpl
    }

    @Singleton
    @Provides
    fun providesExerciseRepository(exerciseRepositoryImpl: ExerciseRepositoryImpl): ExerciseRepository {
        return exerciseRepositoryImpl
    }

    @Singleton
    @Provides
    fun providesGlossaryRepository(glossaryRepositoryImpl: GlossaryRepositoryImpl): GlossaryRepository {
        return glossaryRepositoryImpl
    }
}