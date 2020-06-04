package com.example.aqualang.education.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.interactors.CourseInteractor

class CourseViewModelFactory(
    private val interactor: CourseInteractor,
    private val courseId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseViewModel::class.java)) {
            return CourseViewModel(interactor, courseId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}