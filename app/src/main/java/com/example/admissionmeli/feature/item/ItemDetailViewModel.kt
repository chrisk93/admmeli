package com.example.admissionmeli.feature.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.admissionmeli.R
import com.example.admissionmeli.domain.GetItemDescriptionUseCase
import com.example.admissionmeli.navigation.Screens
import com.example.admissionmeli.core.network.getErrorMessage
import com.example.admissionmeli.core.utils.UiText
import com.example.admissionmeli.dto.ItemDetail
import com.example.admissionmeli.dto.ProductDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemDetailViewModel (
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val getItemDescriptionUseCase: GetItemDescriptionUseCase by lazy { GetItemDescriptionUseCase() }
    private var productDetail = MutableStateFlow<ProductDetail?>(null)

    private var _uiState = MutableStateFlow<ItemDetailUiState>(ItemDetailUiState.Loading)
    val uiState: StateFlow<ItemDetailUiState> = _uiState.asStateFlow()

    init {
        try {
            val productId = savedStateHandle.get<String>(Screens.Detail.ITEM_ID_NAV_KEY)
            productId?.let {
                getProductDescription(it)
            }
            observeProductDetail()

        } catch (e: Exception) {
            _uiState.update {
                ItemDetailUiState.LoadFailed(
                    UiText.StringResource(R.string.item_load_failed)
                )
            }
        }
    }

    private fun getProductDescription(productId: String) {
        viewModelScope.launch {
            getItemDescriptionUseCase(productId)
                .onSuccess { data ->
                    productDetail.update {
                        it?.copy(description = data.description)
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        ItemDetailUiState.LoadFailed(
                            UiText.StringResource(error.getErrorMessage())
                        )
                    }
                }
        }
    }

    private fun observeProductDetail() {
        viewModelScope.launch {
            productDetail.collect { productDetail ->
                productDetail?.let { item ->
                    _uiState.update {
                        ItemDetailUiState.Success(item)
                    }
                }
            }
        }
    }

    fun onItemDetailReceived(itemDetail: ItemDetail) {
        if (productDetail.value == null) {
            productDetail.update {
                ProductDetail(
                    id = itemDetail.id,
                    title = itemDetail.title,
                    price = itemDetail.price,
                    thumbnail = itemDetail.thumbnail,
                    condition = itemDetail.condition,
                    soldQuantity = itemDetail.soldQuantity,
                    availableQuantity = itemDetail.availableQuantity,
                    currencyId = itemDetail.currencyId,
                    description = ""
                )
            }
        }
    }

}