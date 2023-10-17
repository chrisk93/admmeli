package com.example.admissionmeli.domain

import com.example.admissionmeli.data.ItemRepository
import javax.inject.Inject

class GetItemDescriptionUseCase  @Inject constructor(private val itemRepository: ItemRepository) {
    suspend operator fun invoke(productId: String) =
        itemRepository.getProductDescription(productId)
}