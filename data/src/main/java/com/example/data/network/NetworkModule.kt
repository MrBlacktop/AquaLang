package com.example.data.network

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
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providesUserApiService(retrofit: Retrofit): UserApiService{
        return retrofit.create(UserApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesCourseApiService(retrofit: Retrofit): CourseApiService{
        return retrofit.create(CourseApiService::class.java)
    }
}