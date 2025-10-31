// kotlin
package com.example.countrydemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.countrydemo.screen.search.SearchViewModel
import com.example.countrydemo.utils.ResultResponse
import com.example.countrysdk.impl.CountryRepository
import com.example.countrysdk.model.Country
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: CountryRepository
    private lateinit var viewModel: SearchViewModel
    private val dispatcher = StandardTestDispatcher()
    val countries = listOf(
        Country(
            subregion = "vilnius",
            region = "Europe",
            countryName = "Lithuania",
            flagPic = "https://demo.com/flag.png"
        )
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        repository = mockk()

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getCountry list with success`() = runTest {
        coEvery { repository.getAllCountry() } returns Result.success(countries)
        viewModel = SearchViewModel(repository)
        viewModel.getCountry()
        advanceUntilIdle()
        assertTrue(viewModel.uiState.value is ResultResponse.Success)
        assertTrue((viewModel.uiState.value as ResultResponse.Success).data == countries)
    }

    @Test
    fun `getCountry list with error`() = runTest {
        coEvery { repository.getAllCountry() } returns Result.failure(exception = Exception("failed"))
        viewModel = SearchViewModel(repository)
        viewModel.getCountry()
        advanceUntilIdle()
        assertTrue(viewModel.uiState.value is ResultResponse.Error)
    }

    @Test
    fun `findCountryByName with Success`() = runTest {
        coEvery { repository.getAllCountry() } returns Result.success(emptyList())
        coEvery { repository.findCountry("Spain") } returns Result.success(countries)
        viewModel = SearchViewModel(repository)
        viewModel.findCountryByName("Spain")
        advanceUntilIdle()
        assertTrue(viewModel.uiState.value is ResultResponse.Success)
    }

    @Test
    fun `findCountryByName with error`() = runTest {
        coEvery { repository.getAllCountry() } returns Result.failure(Exception("no country found!"))
        coEvery { repository.findCountry("Spain") }  returns Result.failure(Exception("no country found!"))
        viewModel = SearchViewModel(repository)
        viewModel.findCountryByName("Spain")
        advanceUntilIdle()
        assertTrue(viewModel.uiState.value is ResultResponse.Error)
    }

}
