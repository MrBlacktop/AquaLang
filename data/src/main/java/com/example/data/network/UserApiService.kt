package com.example.data.network

import com.example.data.network.models.RegistrationRespondWebModel
import com.example.data.network.models.UserWebModel
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {

    @POST("")
    fun postUserAsync(@Body user: UserWebModel): Deferred<RegistrationRespondWebModel>

    @GET("")
    fun getUserAsync(@Body userName: String, @Body password: String): Deferred<UserWebModel>
}