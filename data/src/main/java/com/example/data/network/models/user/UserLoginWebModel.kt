package com.example.data.network.models.user

import com.example.domain.models.user.UserLogin
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserLoginWebModel(
    @Json(name = "username")
    var userName: String ="",
    var password: String=""
)

fun UserLogin.asWebModel() =
    UserLoginWebModel(userName, password)