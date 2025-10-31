package com.example.countrydemo.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.countrydemo.R
import com.example.countrydemo.component.AppBar
import com.example.countrydemo.component.ImageComponent
import com.example.countrydemo.component.Subtitle
import com.example.countrydemo.component.Title
import com.example.countrydemo.screen.detail.ItemDefaults.ImageSize
import com.example.countrydemo.screen.detail.ItemDefaults.InnerSpacing


@Composable
fun DetailView(
    innerPaddingValues: PaddingValues,
    detailViewModel: DetailViewModel = hiltViewModel(),
    onBackPressed: () -> Unit = {}
) {
    val country = detailViewModel.countryDetailState.value
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(innerPaddingValues)) {
        AppBar(stringResource(R.string.country_detail)) {
            onBackPressed()
        }
        country?.let {
            ImageComponent(
                url = country.flagPic,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ImageSize)
            )
            Spacer(modifier = Modifier.width(InnerSpacing))
            Title(title = country.countryName)
            Subtitle(stringResource(R.string.subregion, country.subregion))
        }
    }
}


private object ItemDefaults {
    val ImageSize = 250.dp
    val InnerSpacing = 12.dp
}
