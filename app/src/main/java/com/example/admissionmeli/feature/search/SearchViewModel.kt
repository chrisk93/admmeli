package com.example.admissionmeli.feature.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.admissionmeli.domain.GetSearchItemUseCase
import com.example.admissionmeli.dto.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update

class SearchViewModel: ViewModel() {
    var searchQuery = mutableStateOf("")
        private set

    val getSearchItemUseCase: GetSearchItemUseCase by lazy { GetSearchItemUseCase() }
    private var _uiState = MutableStateFlow<SearchResultUiState>(SearchResultUiState.EmptyValue)
    val uiState: StateFlow<SearchResultUiState> = _uiState.asStateFlow()

    val searchResults = MutableStateFlow(flowOf(PagingData.empty<Product>()))

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }

    fun onSearchQuerySubmit(query: String) {
        _uiState.update { SearchResultUiState.Loading }
        searchResults.update {
            getSearchItemUseCase(query)
                .cachedIn(viewModelScope)
        }
        _uiState.update { SearchResultUiState.Loaded }
    }
}