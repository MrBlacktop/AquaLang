package com.example.domain.interactors

import com.example.domain.models.user.RegistrationRespond
import com.example.domain.models.user.User
import com.example.domain.models.user.UserLogin
import com.example.domain.repositories.UserRepository

class UserInteractor(private val userRepository: UserRepository) {

    suspend fun registerUser(user: User, password: String): RegistrationRespond {
        return userRepository.addUser(user, password)
    }

    suspend fun loginUser(userLogin: UserLogin) {
        userRepository.logInUser(userLogin)
    }

    fun isUserLoggedOn(): Boolean {
        return userRepository.getUserFromDb() != null
    }

    fun getUser(): User?{
        return userRepository.getUserFromDb()
    }

    suspend fun logout(){
        userRepository.deleteUsers()
    }
}