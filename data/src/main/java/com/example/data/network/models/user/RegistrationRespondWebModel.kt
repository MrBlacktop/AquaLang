package com.example.data.network.models.user

import com.example.domain.models.user.RegistrationRespond
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationRespondWebModel(
    var status: Boolean,
    var message: String
)

fun RegistrationRespondWebModel.asDomainModel() =
    RegistrationRespond(status, message)