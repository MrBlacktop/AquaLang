package com.example.aqualang.education.lesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.domain.interactors.LessonInteractor

class LessonViewModel(lessonInteractor: LessonInteractor, lessonId: Int) : ViewModel() {

    val lesson = lessonInteractor.getLesson(lessonId)

    private val _navigateToExercises = MutableLiveData<Boolean>()
    val navigateToExercises: LiveData<Boolean>
        get() = _navigateToExercises

    fun startExercisesButtonClicked(){
        _navigateToExercises.value = true
    }

    fun doneNavigationToExercises(){
        _navigateToExercises.value = null
    }

}