package com.example.aqualang.education.glossary.word

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.interactors.GlossaryInteractor
import com.example.domain.models.GlossaryWord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class WordViewModel(private val glossaryInteractor: GlossaryInteractor) : ViewModel() {
    val word = GlossaryWord()

    private val uiScope = CoroutineScope(Dispatchers.Main)

    private val _navigateToGlossary = MutableLiveData<Boolean>()
    val navigateToGlossary: LiveData<Boolean>
        get() = _navigateToGlossary

    private val _showNetworkErrorToast = MutableLiveData<Boolean>()
    val showNetworkErrorToast: LiveData<Boolean>
        get() = _showNetworkErrorToast

    fun addButtonClicked() {
        uiScope.launch {
            try {
                glossaryInteractor.addWord(word)
                _navigateToGlossary.value = true

            } catch (e: Exception) {
                Log.e("WordViewModel", e.message ?: "Unknown error")
                _showNetworkErrorToast.value = true
            }
        }
    }

    fun doneNavigationToGlossary() {
        _navigateToGlossary.value = null
    }

    fun doneShowingErrorToast() {
        _showNetworkErrorToast.value = null
    }
}