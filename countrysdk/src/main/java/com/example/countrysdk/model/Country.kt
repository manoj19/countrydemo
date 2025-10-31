package com.example.countrysdk.model

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val subregion: String,
    val region: String,
    val countryName: String,
    val flagPic: String
)
