package com.example.admissionmeli.domain

import com.example.admissionmeli.data.ItemRepositoryImpl

class GetItemDescriptionUseCase () {
    private val itemRepository by lazy { ItemRepositoryImpl() }

    suspend operator fun invoke(productId: String) =
        itemRepository.getProductDescription(productId)
}