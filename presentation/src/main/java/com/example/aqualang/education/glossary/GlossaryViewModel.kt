package com.example.aqualang.education.glossary

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.domain.interactors.GlossaryInteractor
import com.example.domain.models.GlossaryWord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class GlossaryViewModel(private val glossaryInteractor: GlossaryInteractor) : ViewModel() {
    val glossary = glossaryInteractor.getGlossary().asLiveData()

    private val uiScope = CoroutineScope(Dispatchers.Main)

    private val _showNetworkErrorToast = MutableLiveData<Boolean>()
    val showNetworkErrorToast: LiveData<Boolean>
        get() = _showNetworkErrorToast

    init {
        sync()
    }

    private fun sync() {
        uiScope.launch {
            try {
                glossaryInteractor.sync()
            } catch (e: Exception) {
                Log.e("GlossaryViewModel", e.message ?: "Unknown error")
                _showNetworkErrorToast.value = true
            }
        }
    }

    fun doneShowingErrorToast() {
        _showNetworkErrorToast.value = null
    }

    fun deleteWord(word: GlossaryWord) {
        uiScope.launch {
            try {
                glossaryInteractor.deleteWord(word)

            } catch (e: Exception) {
                Log.e("GlossaryViewModel", e.message ?: "Unknown error")
                _showNetworkErrorToast.value = true
            }
        }
    }


}