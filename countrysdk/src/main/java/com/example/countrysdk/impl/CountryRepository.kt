package com.example.countrysdk.impl

import com.example.countrysdk.model.Country
import com.example.infra.model.CountryResponse
import com.example.network.di.ClientApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CountryRepository {
    suspend fun getAllCountry(): Result<List<Country>>
    suspend fun findCountry(search: String): Result<List<Country>>
}

internal class CountryRepositoryImpl @Inject constructor(
    private val clientApi: ClientApi,
) : CountryRepository {

    override suspend fun getAllCountry(): Result<List<Country>> {
        return runCatching {
            withContext(Dispatchers.IO) {
                clientApi.getAllCountries().map { country ->
                    country.mapToCountry()
                }
            }
        }
    }

    override suspend fun findCountry(search: String): Result<List<Country>> {
        return runCatching {
            withContext(Dispatchers.IO) {
                clientApi.filterCountryByName(countryName = search).map { country ->
                    country.mapToCountry()
                }
            }
        }
    }

    private fun CountryResponse.mapToCountry(): Country {
        return Country(
            countryName = countryName.common,
            region = region,
            subregion = subregion,
            flagPic = coatOfArms.flagImage
        )
    }
}