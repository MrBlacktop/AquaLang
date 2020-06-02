package com.example.domain

import com.example.domain.models.RegistrationRespond
import com.example.domain.models.User
import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.flow.count
import java.lang.Exception

class UserInteractor(private val userRepository: UserRepository) {

    suspend fun registerUser(user: User, password: String): RegistrationRespond {
         return userRepository.addUser(user, password)
    }

    suspend fun loginUser(userName: String , password: String): Boolean{
        return try {
            val user = userRepository.getUserFromApi(userName, password)
            userRepository.addUserToDb(user)
            true
        }catch (e: Exception){
            false
        }

    }

    suspend fun isUserLoggedOn(): Boolean{
        return userRepository.getUserFromDb().count() != 0
    }
}