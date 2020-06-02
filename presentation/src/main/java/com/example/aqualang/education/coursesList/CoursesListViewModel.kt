package com.example.aqualang.education.coursesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.domain.LoadCoursesUseCase
import com.example.domain.UserInteractor
import com.example.domain.models.Course
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoursesListViewModel(
    private val userInteractor: UserInteractor,
    private val loadCoursesUseCase: LoadCoursesUseCase
) : ViewModel() {

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean>
        get() = _navigateToLogin

    private val uiScope = CoroutineScope(Dispatchers.Main)

    lateinit var courses: LiveData<List<Course>>

    init {
        checkIfUserLoggedIn()
        loadCourses()
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

    private fun loadCourses() {
        uiScope.launch {
            courses = loadCoursesUseCase.loadCourses().asLiveData()
        }
    }
}