package com.example.countrydemo.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.countrydemo.screen.detail.DetailView
import com.example.countrydemo.screen.detail.DetailViewModel
import com.example.countrydemo.screen.search.SearchView
import com.example.countrydemo.screen.search.SearchViewModel
import com.example.countrysdk.model.Country
import kotlinx.serialization.Serializable

@Composable
fun NavGraph(innerPaddingValues: PaddingValues) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.Search) {
        composable<Destination.Search> { backStackEntry ->
            val searchViewModel: SearchViewModel = hiltViewModel()
            SearchView(
                innerPaddingValues = innerPaddingValues,
                searchViewModel = searchViewModel,
                onNavigateToDetailsPage = { country ->
                    navController.navigate(country)
                }
            )
        }

        composable<Country> { backStackEntry ->
            val detailViewModel: DetailViewModel = hiltViewModel()
            DetailView(
                innerPaddingValues = innerPaddingValues,
                detailViewModel = detailViewModel,
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class Destination {
    @Serializable
    data object Search
}
