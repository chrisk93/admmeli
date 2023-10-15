package com.example.admissionmeli.feature.item

import com.example.admissionmeli.core.utils.UiText
import com.example.admissionmeli.dto.ProductDetail
sealed interface ItemDetailUiState {
    object Loading : ItemDetailUiState
    data class Success(val product: ProductDetail) : ItemDetailUiState
    data class LoadFailed(val message: UiText) : ItemDetailUiState
}