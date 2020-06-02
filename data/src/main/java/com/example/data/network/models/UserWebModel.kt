package com.example.data.network.models

import com.example.domain.models.User
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserWebModel(
    val id: Int,
    var userName: String,
    var email: String,
    var firstName: String,
    var lastName: String,
    var password: String?
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

fun User.asWebModel() = UserWebModel(id, userName, email, firstName, lastName, null)