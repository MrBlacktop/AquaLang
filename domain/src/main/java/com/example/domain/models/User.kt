package com.example.domain.models

data class User(
    val id: Int = 0,
    var userName: String = "",
    var email: String = "",
    var firstName: String = "",
    var lastName: String = ""
)