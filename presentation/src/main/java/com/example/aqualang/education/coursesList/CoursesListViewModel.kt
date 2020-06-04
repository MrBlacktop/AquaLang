package com.example.aqualang.education.coursesList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.domain.interactors.CourseInteractor
import com.example.domain.interactors.UserInteractor
import com.example.domain.models.Course
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoursesListViewModel(
    private val userInteractor: UserInteractor,
    private val loadCoursesUseCase: CourseInteractor
) : ViewModel() {

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean>
        get() = _navigateToLogin

    private val uiScope = CoroutineScope(Dispatchers.Main)

    val courses: LiveData<List<Course>> = loadCoursesUseCase.loadCourses().asLiveData()

    init {
        checkIfUserLoggedIn()
        sync()
    }

    fun doneNavigatingToLogin() {
        _navigateToLogin.value = null
    }

    private fun checkIfUserLoggedIn() {
        uiScope.launch {
            if (!userInteractor.isUserLoggedOn())
                _navigateToLogin.value = true
        }
    }

    private fun sync() {
        uiScope.launch {
            loadCoursesUseCase.sync()
        }
    }
}