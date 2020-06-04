package com.example.aqualang.education.coursesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.interactors.CourseInteractor
import com.example.domain.interactors.UserInteractor
import javax.inject.Inject

class CoursesListViewModelFactory @Inject constructor(
    private val interactor: UserInteractor,
    private val useCase: CourseInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoursesListViewModel::class.java)) {
            return CoursesListViewModel(interactor, useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}