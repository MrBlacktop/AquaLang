package com.example.data.network.apiServices

import com.example.data.network.models.user.UserLoginWebModel
import com.example.data.network.models.user.RegistrationRespondWebModel
import com.example.data.network.models.user.UserWebModel
import com.example.data.network.models.user.UserWithTokenWebModel
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserApiService {

    @POST("users/")
    fun postUserAsync(@Body user: UserWebModel): Deferred<RegistrationRespondWebModel>

    @POST("users/login/")
    fun getUserAsync(@Body login: UserLoginWebModel): Deferred<UserWithTokenWebModel>
}