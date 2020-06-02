package com.example.data.network.models

import com.example.domain.models.RegistrationRespond
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationRespondWebModel(
    var status: Boolean,
    var description: String
)

fun RegistrationRespondWebModel.asDomainModel() =
    RegistrationRespond(status, description)