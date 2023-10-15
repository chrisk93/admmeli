package com.example.admissionmeli.feature.search

sealed interface SearchResultUiState {
    object Loading : SearchResultUiState
    object EmptyValue : SearchResultUiState
    object Loaded : SearchResultUiState
}