package com.example.domain.repositories

import com.example.domain.models.user.RegistrationRespond
import com.example.domain.models.user.User
import com.example.domain.models.user.UserLogin

interface UserRepository {
    suspend fun addUser(user: User, password: String): RegistrationRespond
    suspend fun logInUser(userLogin: UserLogin): User
    fun getUserFromDb(): User?
    suspend fun deleteUsers()
    fun getUserToken(): String
}