package com.example.countrydemo.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.countrydemo.R
import com.example.countrydemo.utils.ResultResponse
import com.example.countrysdk.model.Country
import kotlinx.coroutines.FlowPreview


@OptIn(FlowPreview::class)
@Composable
fun SearchView(
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val uiState: ResultResponse<List<Country>> by searchViewModel.uiState.observeAsState(
        ResultResponse.Loading
    )



    Column(modifier = Modifier.fillMaxSize()) {


        Box(modifier = Modifier.fillMaxSize()) {
            when (val result = uiState) {
                is ResultResponse.Error -> {
                    Text(
                        stringResource(R.string.no_data_found),
                        modifier = Modifier.align(alignment = Alignment.Center),

                        )
                }

                ResultResponse.Loading -> {
                    CircularProgressIndicator(
                        color = Color.Red,
                        modifier = Modifier.align(alignment = Alignment.Center)
                    )
                }

                is ResultResponse.Success<List<Country>> -> {
                    LazyColumn {
                        itemsIndexed(result.data) { index, item ->
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column(
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = item.countryName,
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.colorScheme.onBackground,

                                        )
                                        Text(
                                            text = stringResource(R.string.region, item.region),
                                            style = MaterialTheme.typography.titleSmall,
                                            color = MaterialTheme.colorScheme.onBackground
                                        )
                                    }
                                }
                                HorizontalDivider(color = MaterialTheme.colorScheme.primary )
                            }
                        }
                    }
                }
            }
        }
    }
}