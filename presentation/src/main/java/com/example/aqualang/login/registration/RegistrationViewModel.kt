package com.example.aqualang.login.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.user.User
import com.example.domain.interactors.UserInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel(private val userInteractor: UserInteractor) : ViewModel() {

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean>
        get() = _navigateToLogin

    private val _showToast = MutableLiveData<Boolean>()
    val showToast: LiveData<Boolean>
        get() = _showToast

    var toastText = ""

    val user = MutableLiveData<User>()
    var password = MutableLiveData<String>()

    init {
        user.value = User()
        password.value = ""
    }

    private val uiScope = CoroutineScope(Dispatchers.Main)

    fun saveButtonClicked() {
        uiScope.launch {
            try {
                val respond = userInteractor.registerUser(user.value!!, password.value!!)
                toastText = respond.description
                _showToast.value = true
                if (respond.status)
                    _navigateToLogin.value = true
            } catch (e: Exception) {
                Log.e("RegistrationViewModel", e.message ?: "")
                toastText = e.message.toString()
                _showToast.value = true
            }
        }
    }

    fun doneNavigationToLogin() {
        _navigateToLogin.value = null
    }

    fun doneShowingToast() {
        _showToast.value = null
    }
}