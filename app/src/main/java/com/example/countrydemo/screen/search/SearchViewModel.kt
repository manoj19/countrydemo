package com.example.countrydemo.screen.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrydemo.utils.ResultResponse
import com.example.countrysdk.impl.CountryRepository
import com.example.countrysdk.model.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<ResultResponse<List<Country>>>()
    val uiState: LiveData<ResultResponse<List<Country>>> = _uiState

    init {
        getCountry()
    }

    fun getCountry() {
        _uiState.value = ResultResponse.Loading
        viewModelScope.launch {
            countryRepository.getAllCountry().onSuccess {
                _uiState.value = ResultResponse.Success(it)
            }.onFailure {
                _uiState.value = ResultResponse.Error(it.message ?: "")
            }
        }
    }

    fun findCountryByName(search: String) {
        _uiState.value = ResultResponse.Loading
        viewModelScope.launch {
            countryRepository.findCountry(search).onSuccess {
                _uiState.value = ResultResponse.Success(it)
            }.onFailure {
                _uiState.value = ResultResponse.Error(it.message ?: "")
            }
        }
    }

}
