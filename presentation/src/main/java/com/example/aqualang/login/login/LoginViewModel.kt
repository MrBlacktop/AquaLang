package com.example.aqualang.login.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.UserInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

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


    private val uiScope = CoroutineScope(Dispatchers.Main)

    fun signInButtonClicked(){
        uiScope.launch {
            val loginUser = userInteractor.loginUser(userName.value!!, password.value!!)
            if (loginUser)
                _navigateToCoursesList.value = true
            else
                _showLoginErrorToast.value = true
        }
    }

    fun signUpButtonClicked(){
        _navigateToRegistration.value = true
    }

    fun doneNavigationToCoursesList(){
        _navigateToCoursesList.value = null
    }

    fun doneNavigatingToRegistration(){
        _navigateToRegistration.value = null
    }

    fun doneShowingErrorToast(){
        _showLoginErrorToast.value = null
    }


}