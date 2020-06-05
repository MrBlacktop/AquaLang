package com.example.aqualang.education.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.interactors.CourseInteractor
import com.example.domain.interactors.TopicInteractor

class CourseViewModelFactory(
    private val interactor: CourseInteractor,
    private val topicInteractor: TopicInteractor,
    private val courseId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseViewModel::class.java)) {
            return CourseViewModel(interactor,topicInteractor, courseId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}