package com.example.aqualang.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.interactors.UserInteractor
import javax.inject.Inject

class UserViewModelFactory @Inject constructor(
    private val interactor: UserInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(interactor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}