package com.example.countrydemo.component


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.countrydemo.R

@Composable
fun ImageComponent(
    url: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .error(android.R.drawable.ic_menu_report_image)
            .placeholder(R.drawable.ic_launcher_background)
            .build(),
        contentDescription = "",
        modifier = modifier
    )
}