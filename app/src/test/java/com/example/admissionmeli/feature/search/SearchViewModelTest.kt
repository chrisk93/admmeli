package com.example.admissionmeli.feature.search

import app.cash.turbine.test
import com.example.admissionmeli.domain.GetSearchItemUseCase
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class SearchViewModelTest {

    lateinit var searchViewModel: SearchViewModel
    @Before
    fun setUp() {
        val getSearchItemUseCase: GetSearchItemUseCase = mockk(relaxed = true)
        searchViewModel = SearchViewModel(getSearchItemUseCase)
    }

    @Test
    fun `onSearchQueryChanged update value`() {
        searchViewModel.onSearchQueryChanged("text")

        Assert.assertEquals("text", searchViewModel.searchQuery.value)
    }

    @Test
    fun `onSearchQuerySubmit update value`() = runBlocking {
        searchViewModel.onSearchQuerySubmit("query")

        searchViewModel.uiState.test {
            val state = awaitItem()
            Assert.assertTrue(state is SearchResultUiState.Loaded)
        }
    }


}