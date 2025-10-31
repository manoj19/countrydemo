package com.example.countrysdk.di


import com.example.countrysdk.impl.CountryRepository
import com.example.countrysdk.impl.CountryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DemoSdkModule {

    @Binds
    internal abstract fun countryRepositoryImpl(countryRepositoryImpl: CountryRepositoryImpl): CountryRepository

}
