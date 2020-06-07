package com.example.aqualang.education.exercises.controlFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.useCases.LoadExercisesUseCase
import javax.inject.Inject

class ExerciseControlViewModelFactory @Inject constructor(
    private val useCase: LoadExercisesUseCase,
    private val lessonId: Int?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseControlViewModel::class.java)) {
            return ExerciseControlViewModel(useCase, lessonId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}