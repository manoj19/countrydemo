package com.example.countrydemo.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.countrydemo.R
import com.example.countrysdk.model.Country


@Composable
fun CountryItem(
    country: Country,
    onClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
        ) {

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Title(country.countryName)
                Title(stringResource(R.string.region, country.region))
                Subtitle(stringResource(R.string.subregion, country.subregion))
            }
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.onBackground )
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        maxLines = 2,
        modifier = Modifier.padding(bottom = 0.dp)
    )
}

@Composable
fun Subtitle(subtitle: String) {
    Text(
        text = subtitle,
        style = MaterialTheme.typography.titleSmall,
        maxLines = 1,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = .7f),
        modifier = Modifier.padding(bottom = 0.dp)
    )
}