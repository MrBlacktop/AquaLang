package com.example.aqualang.education.exercises.controlFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.exercise.Answer
import com.example.domain.models.exercise.Exercise
import com.example.domain.models.exercise.SubmittedAnswer
import com.example.domain.useCases.LoadExercisesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseControlViewModel(
    private val loadExercisesUseCase: LoadExercisesUseCase,
    private val lessonId: Int?
) : ViewModel() {

    private val uiScope = CoroutineScope(Dispatchers.Main)
    var exercises = MutableLiveData<List<Exercise>>()

    var submittedAnswers =
        mutableListOf<SubmittedAnswer>()

    private val _showNetworkErrorToast = MutableLiveData<Boolean>()
    val showNetworkErrorToast: LiveData<Boolean>
        get() = _showNetworkErrorToast

    private val _navigateToResults = MutableLiveData<Boolean>()
    val navigateToResults: LiveData<Boolean>
        get() = _navigateToResults

    init {
        exercises.value = mutableListOf()
        loadExercises()
    }


    fun registerAnswer(isCorrect: Boolean, exerciseId: Int) {
        val answer = SubmittedAnswer(exerciseId, isCorrect)
        submittedAnswers.add(answer)
        if (submittedAnswers.size == exercises.value!!.size) _navigateToResults.value = true
    }

    private fun loadExercises() {
        try {
            uiScope.launch {
                lessonId?.let {
                    exercises.value = loadExercisesUseCase.loadExercises(it)
                }
            }
        } catch (e: Exception) {
            Log.e("ExerciseControlViewModel", e.message ?: "unknown error")
            _showNetworkErrorToast.value = true
        }
    }

    fun doneShowingErrorToast() {
        _showNetworkErrorToast.value = null
    }

    fun doneNavigationToResults(){
        _navigateToResults.value = null
    }

    fun sendAnswers(){
        uiScope.launch {
            loadExercisesUseCase.sendResults(submittedAnswers)
        }
    }
}