package com.example.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.User

@Entity(tableName = "user")
data class UserDbModel(
    @PrimaryKey
    val id:Int,
    var userName: String,
    var email: String,
    var firstName:String,
    var lastName: String
)

fun UserDbModel.asDomainModel() =
    User(id, userName, email, firstName, lastName)
fun User.asDbModel() = UserDbModel(
    id,
    userName,
    email,
    firstName,
    lastName
)