package com.example.countrysdk.impl

import com.example.countrysdk.model.Country
import com.example.network.di.ClientApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CountryRepository {
    suspend fun getAllCountry(): Result<List<Country>>
}

internal class CountryRepositoryImpl @Inject constructor(
    private val clientApi: ClientApi,
) : CountryRepository {

    override suspend fun getAllCountry(): Result<List<Country>> {
        return runCatching {
            withContext(Dispatchers.IO) {
                clientApi.getAllCountries().map { country ->
                    Country(
                        countryName = country.countryName.common,
                        region = country.region,
                        subregion = country.subregion,
                        flagPic = country.coatOfArms.flagImage
                    )
                }
            }
        }
    }
}