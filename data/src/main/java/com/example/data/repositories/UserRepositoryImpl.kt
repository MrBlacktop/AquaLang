package com.example.data.repositories

import android.util.Log
import com.example.data.database.AquaLangDatabaseDao
import com.example.data.database.models.asDomainModel
import com.example.data.database.models.getToken
import com.example.data.network.apiServices.UserApiService
import com.example.data.network.models.user.asDbModel
import com.example.data.network.models.user.asDomainModel
import com.example.data.network.models.user.asWebModel
import com.example.domain.models.user.RegistrationRespond
import com.example.domain.models.user.User
import com.example.domain.models.user.UserLogin
import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.Exception

class UserRepositoryImpl @Inject constructor(
    private val dao: AquaLangDatabaseDao,
    private val userApiService: UserApiService
) :
    UserRepository {

    override suspend fun addUser(user: User, password: String): RegistrationRespond {
        val userWebModel = user.asWebModel(password)
        val respond = userApiService.postUserAsync(userWebModel).await()
        return respond.asDomainModel()
    }

    override suspend fun logInUser(userLogin: UserLogin): User {
        val user = userApiService.getUserAsync(userLogin.asWebModel()).await()
        Log.e("UserRepository",user.toString())
        withContext(Dispatchers.IO){
            dao.insert(user.asDbModel())
        }
        return user.asDomainModel()
    }

    override fun getUserFromDb(): User? {
        val asDomainModel = dao.getUser()?.asDomainModel()
        Log.e("UserRep",asDomainModel.toString())
        return asDomainModel
    }

    override suspend fun deleteUsers() {
        withContext(Dispatchers.IO){
            dao.clearUsers()
        }
    }

    override fun getUserToken(): String {
        val user = dao.getUser() ?: throw  Exception("Current user not found")
        return user.getToken()
    }


}