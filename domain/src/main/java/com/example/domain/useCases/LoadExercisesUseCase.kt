package com.example.domain.useCases

import com.example.domain.models.exercise.Exercise
import com.example.domain.models.exercise.SubmittedAnswer
import com.example.domain.repositories.ExerciseRepository
import com.example.domain.repositories.UserRepository

class LoadExercisesUseCase(private val exerciseRepository: ExerciseRepository,private val userRepository: UserRepository){
    suspend fun loadExercises(lessonId:Int): List<Exercise>{
        val token = userRepository.getUserToken()
        return exerciseRepository.getLessonExercises(lessonId,token)
    }

    suspend fun sendResults(answers: List<SubmittedAnswer>){
        val token = userRepository.getUserToken()
        exerciseRepository.sendExercisesResult(answers,token)
    }
}