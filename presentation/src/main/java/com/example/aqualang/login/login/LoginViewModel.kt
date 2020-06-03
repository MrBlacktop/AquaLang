package com.example.aqualang.login.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.UserInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.Exception

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

    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    init {
        userName.value = ""
        password.value = ""
    }

    private val uiScope = CoroutineScope(Dispatchers.Main)

    fun signInButtonClicked() {
        uiScope.launch {
            try {
                userInteractor.loginUser(userName.value!!, password.value!!)
                _navigateToCoursesList.value = true
            } catch (e: Exception) {
                _showLoginErrorToast.value = true
                Log.e("LoginViewModel", e.message ?: "K")
            }
        }
    }

    fun signUpButtonClicked() {
        _navigateToRegistration.value = true
    }

    fun doneNavigationToCoursesList() {
        _navigateToCoursesList.value = null
    }

    fun doneNavigatingToRegistration() {
        _navigateToRegistration.value = null
    }

    fun doneShowingErrorToast() {
        _showLoginErrorToast.value = null
    }


}