package com.example.admissionmeli.dto.remote

import com.example.admissionmeli.dto.ProductDescription
import com.squareup.moshi.Json

data class ProductDescriptionResponse(
    @field:Json(name = "plain_text") val description: String,
) {
    fun toProductDescription(): ProductDescription = ProductDescription(description)
}
