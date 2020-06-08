package com.example.aqualang.education.glossary.word

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.interactors.GlossaryInteractor
import javax.inject.Inject

class WordViewModelFactory @Inject constructor(private val glossaryInteractor: GlossaryInteractor) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            return WordViewModel(glossaryInteractor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}