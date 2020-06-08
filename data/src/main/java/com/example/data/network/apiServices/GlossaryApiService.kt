package com.example.data.network.apiServices

import com.example.data.network.models.GlossaryWordWebModel
import com.example.data.network.models.user.RegistrationRespondWebModel
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface GlossaryApiService {

    @GET("users/{id}/dictionary/")
    fun getGlossaryAsync(
        @Path("id") userId: Int,
        @Header("Authorization") token: String
    ): Deferred<List<GlossaryWordWebModel>>

    @POST("words/post_from_user/")
    fun postWordAsync(
        @Header("Authorization") token: String,
        @Body word: GlossaryWordWebModel
    ): Deferred<RegistrationRespondWebModel>

    @HTTP(method = "DELETE", path = "/words/{id}/delete_from_user/", hasBody = false)
    fun deleteWordAsync(
        @Header("Authorization") token: String,
        @Path("id") wordId: Int
    ): Deferred<RegistrationRespondWebModel>

}