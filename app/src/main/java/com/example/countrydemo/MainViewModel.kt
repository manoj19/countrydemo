package com.example.countrydemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrysdk.impl.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val countryRepository: CountryRepository): ViewModel() {

    fun getAllCountry() {
        viewModelScope.launch {
           val result= countryRepository.getAllCountry()
            println(result)
        }
    }


}