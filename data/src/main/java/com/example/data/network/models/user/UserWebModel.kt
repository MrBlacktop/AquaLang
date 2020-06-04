package com.example.data.network.models.user

import com.example.domain.models.user.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserWebModel(
    var id: Int,
    @Json(name = "username")
    var userName: String,

    @Json(name = "first_name")
    var firstName: String,
    @Json(name = "last_name")
    var lastName: String,
    var email: String,
    var password: String
)

fun UserWebModel.asDomainModel(): User {
    return User(
        id,
        userName,
        email,
        firstName,
        lastName
    )
}

fun User.asWebModel(password: String) =
    UserWebModel(
        id,
        userName,
        firstName,
        lastName,
        email,
        password
    )