package com.example.network.di

import com.example.infra.model.CountryResponse
import retrofit2.http.GET


interface ClientApi {

    @GET("all?fields=name,region,subregion,coatOfArms")
    suspend fun getAllCountries(): List<CountryResponse>

}