package com.example.aqualang.di

import com.example.aqualang.education.exercises.ExerciseComponent
import com.example.aqualang.login.LoginComponent
import dagger.Module

@Module(subcomponents = [LoginComponent::class, ExerciseComponent::class])
class AppSubComponents {
}