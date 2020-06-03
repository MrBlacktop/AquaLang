package com.example.data.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Login(
    @Json(name = "username")
    var userName: String,
    var password: String
)