package com.example.admissionmeli.data.remote

import com.example.admissionmeli.dto.remote.ProductDescriptionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemApi {

    @GET("/items/{id}/description")
    suspend fun getProductDescription(
        @Path("id") itemId: String,
    ): Response<ProductDescriptionResponse>

}