package com.example.countrydemo.screen.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.countrysdk.model.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val countryDetail = savedStateHandle.toRoute<Country>()
    private val _countryDetail = MutableLiveData<Country>()
    val countryDetailState = _countryDetail

    init {
        _countryDetail.value = countryDetail
    }
}
