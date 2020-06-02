package com.example.data.repositories

import com.example.data.database.AquaLangDatabaseDao
import com.example.data.database.models.asDbModel
import com.example.data.database.models.asDomainModel
import com.example.data.network.UserApiService
import com.example.data.network.models.RegistrationRespondWebModel
import com.example.data.network.models.asDomainModel
import com.example.data.network.models.asWebModel
import com.example.domain.models.RegistrationRespond
import com.example.domain.models.User
import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val dao: AquaLangDatabaseDao,private val userApiService: UserApiService) :
    UserRepository {

    override suspend fun addUser(user: User, password: String): RegistrationRespond {
        var respond = RegistrationRespondWebModel(false,"")
        withContext(Dispatchers.IO){
            val userWebModel = user.asWebModel()
            userWebModel.password = password
             respond = userApiService.postUserAsync(userWebModel).await()
        }
        return respond.asDomainModel()
    }

    override suspend fun getUserFromApi(userName: String, password: String): User {
        return userApiService.getUserAsync(userName,password).await().asDomainModel()
    }

    override fun getUserFromDb(): Flow<User> {
        return dao.getUser().map { it.asDomainModel() }
    }

    override suspend fun addUserToDb(user: User) {
        withContext(Dispatchers.IO){
            dao.insert(user.asDbModel())
        }
    }

}