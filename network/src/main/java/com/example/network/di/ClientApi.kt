package com.example.network.di

import com.example.infra.model.CountryResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface ClientApi {

    @GET("all?fields=name,region,subregion,coatOfArms")
    suspend fun getAllCountries(): List<CountryResponse>

    @GET("name/{name}?fields=name,region,subregion,coatOfArms")
    suspend fun filterCountryByName(@Path("name") countryName: String): List<CountryResponse>

}