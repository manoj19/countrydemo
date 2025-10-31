package com.example.infra.model

import com.google.gson.annotations.SerializedName


data class CountryResponse(
    @SerializedName("subregion") val subregion: String,
    @SerializedName("region") val region: String,
    @SerializedName("name") val countryName: CountryName,
    @SerializedName("coatOfArms") val coatOfArms: CountryFlag
)

data class CountryName(
    @SerializedName("common") val common: String,
    @SerializedName("official") val official: String
)

data class CountryFlag(
    @SerializedName("png") val flagImage: String
)
