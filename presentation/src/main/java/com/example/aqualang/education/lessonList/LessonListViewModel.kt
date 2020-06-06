package com.example.aqualang.education.lessonList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.domain.interactors.LessonInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LessonListViewModel(private val lessonInteractor: LessonInteractor, private val topicId:Int) : ViewModel() {
    val lessons = lessonInteractor.getLessons(topicId).asLiveData()

    private val uiScope = CoroutineScope(Dispatchers.Main)

    private val _showNetworkErrorToast = MutableLiveData<Boolean>()
    val showNetworkErrorToast: LiveData<Boolean>
    get() = _showNetworkErrorToast

    init {
        sync()
    }

    fun doneShowingToast(){
        _showNetworkErrorToast.value = null
    }

    private fun sync(){
        try {
            uiScope.launch {
                lessonInteractor.sync(topicId)
            }
        }catch (e: Exception){
            _showNetworkErrorToast.value = true
            Log.e("LessonListViewModel",e.message ?: "Unknown error")
        }
    }
}