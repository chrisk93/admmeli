package com.example.admissionmeli.feature.search.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.admissionmeli.navigation.Screens
import com.example.admissionmeli.dto.Product
import com.example.admissionmeli.feature.search.SearchScreen
import com.example.admissionmeli.feature.search.SearchViewModel

fun NavGraphBuilder.searchNavigation(
    onNavigateToItemDetail: (Product) -> Unit,
) {
    composable(route = Screens.Search.route) {

        val searchViewModel: SearchViewModel = viewModel()
        val searchQuery by searchViewModel.searchQuery
        val uiState by searchViewModel.uiState.collectAsState()

        val searchResults =
            searchViewModel.searchResults.collectAsState().value.collectAsLazyPagingItems()

        SearchScreen(
            searchQuery = searchQuery,
            onSearchQueryChanged = searchViewModel::onSearchQueryChanged,
            onSearchQuerySubmit = searchViewModel::onSearchQuerySubmit,
            onSearchItemClick = onNavigateToItemDetail,
            state = uiState,
            searchResults = searchResults,
            onBackClick = {}
        )
    }
}