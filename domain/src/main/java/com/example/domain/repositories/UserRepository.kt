package com.example.domain.repositories

import com.example.domain.models.RegistrationRespond
import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun addUser(user: User, password: String): RegistrationRespond
    suspend fun getUserFromApi(userName: String, password: String): User
    fun getUserFromDb(): Flow<User>
    suspend fun addUserToDb(user: User)


}