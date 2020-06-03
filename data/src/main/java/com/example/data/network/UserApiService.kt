package com.example.data.network

import com.example.data.network.models.Login
import com.example.data.network.models.RegistrationRespondWebModel
import com.example.data.network.models.UserWebModel
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface UserApiService {

    @POST("user/")
    fun postUserAsync(@Body user: UserWebModel): Deferred<RegistrationRespondWebModel>

    @POST("user/")
    fun getUserAsync(@Body login: Login): Deferred<UserWebModel>
}