package com.example.aqualang.education.lessonList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.interactors.LessonInteractor

class LessonListViewModelFactory(private val interactor: LessonInteractor, private val topicId: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LessonListViewModel::class.java)) {
            return LessonListViewModel(interactor, topicId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}