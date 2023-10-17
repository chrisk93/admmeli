package com.example.admissionmeli.data

import com.example.admissionmeli.core.network.fold
import com.example.admissionmeli.core.network.makeSafeRequest
import com.example.admissionmeli.data.remote.ItemApi
import com.example.admissionmeli.dto.ProductDescription
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(private val itemApi: ItemApi): ItemRepository {
    /*private val itemApi: ItemApi by lazy {
        InitializeNetwork.provideRetrofit().create(ItemApi::class.java)
    }*/

    override suspend fun getProductDescription(itemId: String): Result<ProductDescription> {
        val response = makeSafeRequest { itemApi.getProductDescription(itemId) }

        return response.fold(
            onSuccess = { data ->
                Result.success(data.toProductDescription())
            },
            onError = { _, message ->
                Result.failure(Exception(message))
            },
            onException = {
                Result.failure(it)
            }
        )
    }
}