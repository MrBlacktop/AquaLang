package com.example.aqualang.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.interactors.UserInteractor
import com.example.domain.models.user.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val userInteractor: UserInteractor) : ViewModel() {
    val user = userInteractor.getUser() ?: User()

    private val uiScope = CoroutineScope(Dispatchers.Main)

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean>
        get() = _navigateToLogin

    fun logoutButtonClicked(){
        uiScope.launch {
            userInteractor.logout()
            _navigateToLogin.value = true
        }
    }
}