package com.example.aqualang.education.lesson

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aqualang.education.lessonList.LessonListViewModel
import com.example.domain.interactors.LessonInteractor

class LessonViewModelFactory(
    private val lessonInteractor: LessonInteractor,
    private val lessonId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LessonViewModel::class.java)) {
            return LessonViewModel(lessonInteractor, lessonId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}