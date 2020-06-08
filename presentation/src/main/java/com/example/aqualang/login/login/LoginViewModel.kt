package com.example.aqualang.login.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.interactors.UserInteractor
import com.example.domain.models.user.UserLogin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val userInteractor: UserInteractor) : ViewModel() {
    private val _navigateToCoursesList = MutableLiveData<Boolean>()
    val navigateToCoursesList: LiveData<Boolean>
        get() = _navigateToCoursesList

    private val _navigateToRegistration = MutableLiveData<Boolean>()
    val navigateToRegistration: LiveData<Boolean>
        get() = _navigateToRegistration

    private val _showLoginErrorToast = MutableLiveData<Boolean>()
    val showLoginErrorToast: LiveData<Boolean>
        get() = _showLoginErrorToast


    val userLogin = MutableLiveData<UserLogin>()


    private val uiScope = CoroutineScope(Dispatchers.Main)

    init {
        userLogin.value = UserLogin()
    }

    fun signInButtonClicked() {
        userLogin.value?.let {
            uiScope.launch {
                try {
                    userInteractor.loginUser(it)
                    _navigateToCoursesList.value = true
                } catch (e: Exception) {
                    _showLoginErrorToast.value = true
                    Log.e("LoginViewModel", e.message ?: "Unexpected error")
                }
            }
        }
    }

    fun signUpButtonClicked() {
        _navigateToRegistration.value = true
    }


    fun doneNavigatingToRegistration() {
        _navigateToRegistration.value = null
    }

    fun doneShowingErrorToast() {
        _showLoginErrorToast.value = null
    }


}