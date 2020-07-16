package com.example.aqualang.education.glossary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aqualang.education.course.CourseViewModel
import com.example.domain.interactors.GlossaryInteractor
import javax.inject.Inject

class GlossaryViewModelFactory @Inject constructor(private val interactor: GlossaryInteractor) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GlossaryViewModel::class.java)) {
            return GlossaryViewModel(interactor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}