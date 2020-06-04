package com.example.data.network.models.user

import com.example.data.database.models.UserDbModel
import com.example.domain.models.user.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserWithTokenWebModel(
    var token: String,
    @Json(name = "user")
    var user: UserWithoutPassword
) {
    @JsonClass(generateAdapter = true)
      data class UserWithoutPassword(
        var id: Int,
        @Json(name = "username")
        var userName: String,

        @Json(name = "first_name")
        var firstName: String,
        @Json(name = "last_name")
        var lastName: String,
        var email: String
    )

}

fun UserWithTokenWebModel.asDomainModel() = User(user.id,user.userName,user.email,user.firstName,user.lastName)

fun UserWithTokenWebModel.asDbModel(): UserDbModel {
    return UserDbModel(
        id = user.id,
        userName = user.userName,
        email = user.email,
        firstName = user.firstName,
        lastName = user.lastName,
        token = token
    )
}