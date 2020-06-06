package com.example.aqualang.login.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.interactors.UserInteractor
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(private val interactor: UserInteractor) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(interactor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}