package com.example.aqualang.education.course

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.domain.interactors.CourseInteractor
import com.example.domain.interactors.TopicInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CourseViewModel(
    private val courseInteractor: CourseInteractor,
    private val topicInteractor: TopicInteractor,
    private val courseId: Int
) : ViewModel() {
    val course = courseInteractor.getCourse(courseId)

    val topics = topicInteractor.getTopics(courseId).asLiveData()

    private val uiScope = CoroutineScope(Dispatchers.Main)

    private val _showNetworkErrorMessage = MutableLiveData<Boolean>()
    val showNetworkErrorMessage: LiveData<Boolean>
        get() = _showNetworkErrorMessage

    private val _courseIsActive = MutableLiveData<Boolean>()
    val courseIsActive: LiveData<Boolean>
        get() = _courseIsActive

    init {
        _courseIsActive.value = course.isActive
        sync()
    }

    fun doneShowingToast() {
        _showNetworkErrorMessage.value = null
    }

    private fun sync() {
        try {
            uiScope.launch {
                topicInteractor.sync(courseId)
            }
        } catch (e: Exception) {
            Log.e("CourseViewModel - Topic", e.message ?: "unknown error")
            _showNetworkErrorMessage.value = true
        }
    }

    fun subscribeButtonClicked() {
        try {
            uiScope.launch {
                val result = courseInteractor.subscribe(course)

                if(result) _courseIsActive.value = course.isActive
            }
        } catch (e: Exception) {
            Log.e("CourseViewModel - Topic", e.message ?: "unknown error")
            _showNetworkErrorMessage.value = true
        }
    }

    fun unsubscribeButtonClicked() {
        try {
            uiScope.launch {
                val result = courseInteractor.unsubscribe(course)

                if(result) _courseIsActive.value = course.isActive
            }

        } catch (e: Exception) {
            Log.e("CourseViewModel - Topic", e.message ?: "unknown error")
            _showNetworkErrorMessage.value = true
        }
    }

}