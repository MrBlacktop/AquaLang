package com.example.aqualang.login.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.UserInteractor
import javax.inject.Inject

class RegistrationViewModelFactory @Inject constructor(private val interactor: UserInteractor): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(interactor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}