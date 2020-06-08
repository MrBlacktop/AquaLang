package com.example.data.network

import com.example.data.network.apiServices.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://aqualang.herokuapp.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providesUserApiService(retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)

    @Singleton
    @Provides
    fun providesCourseApiService(retrofit: Retrofit): CourseApiService =
        retrofit.create(CourseApiService::class.java)

    @Singleton
    @Provides
    fun providesTopicApiService(retrofit: Retrofit): TopicApiService =
        retrofit.create(TopicApiService::class.java)

    @Singleton
    @Provides
    fun providesLessonApiService(retrofit: Retrofit): LessonApiService =
        retrofit.create(LessonApiService::class.java)

    @Singleton
    @Provides
    fun providesExerciseApiService(retrofit: Retrofit): ExerciseApiService =
        retrofit.create(ExerciseApiService::class.java)

    @Singleton
    @Provides
    fun providesGlossaryApiService(retrofit: Retrofit): GlossaryApiService =
        retrofit.create(GlossaryApiService::class.java)
}