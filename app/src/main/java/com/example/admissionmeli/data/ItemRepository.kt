package com.example.admissionmeli.data

import com.example.admissionmeli.dto.ProductDescription

interface ItemRepository {
    suspend fun getProductDescription(itemId: String): Result<ProductDescription>
}