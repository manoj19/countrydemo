package com.example.countrydemo.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.countrydemo.R
import com.example.countrydemo.component.AppBar
import com.example.countrydemo.component.CircularProgressBar
import com.example.countrydemo.component.CountryItem
import com.example.countrydemo.utils.ResultResponse
import com.example.countrysdk.model.Country


@Composable
fun SearchView(
    searchViewModel: SearchViewModel = hiltViewModel(),
    innerPaddingValues: PaddingValues
) {
    val uiState: ResultResponse<List<Country>> by searchViewModel.uiState.observeAsState(
        ResultResponse.Loading
    )

    Column(modifier = Modifier.fillMaxSize().padding(innerPaddingValues)) {
        AppBar(stringResource(R.string.country), isBackBtnVisible = false)

        Box(modifier = Modifier.fillMaxSize()) {
            when (val result = uiState) {
                is ResultResponse.Error -> {
                    Text(
                        stringResource(R.string.no_data_found),
                        modifier = Modifier.align(alignment = Alignment.Center),

                        )
                }

                ResultResponse.Loading -> {
                    CircularProgressBar()
                }

                is ResultResponse.Success<List<Country>> -> {
                    LazyColumn {
                        itemsIndexed(result.data) { index, item ->
                            CountryItem(country = item){

                            }
                        }
                    }
                }
            }
        }
    }
}