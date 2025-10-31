package com.example.countrydemo.screen.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrydemo.utils.ResultResponse
import com.example.countrysdk.impl.CountryRepository
import com.example.countrysdk.model.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

const val debounceTime = 300L

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<ResultResponse<List<Country>>>()
    val uiState: LiveData<ResultResponse<List<Country>>> = _uiState
    val searchQuery = MutableStateFlow("")

    init {
        viewModelScope.launch {
            searchQuery
                .debounce(debounceTime)
                .distinctUntilChanged()
                .collectLatest { text ->
                    if (text.isEmpty()) getCountry()
                    else findCountryByName(text)
                }
        }
    }

    fun searchQuery(search: String) {
        searchQuery.value = search
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
