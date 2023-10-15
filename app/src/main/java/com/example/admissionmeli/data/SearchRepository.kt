package com.example.admissionmeli.data

import androidx.paging.PagingData
import com.example.admissionmeli.dto.Product
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchPaging(query: String): Flow<PagingData<Product>>
}