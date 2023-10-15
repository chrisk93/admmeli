package com.example.admissionmeli.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.admissionmeli.core.network.InitializeNetwork
import com.example.admissionmeli.data.remote.SearchApi
import com.example.admissionmeli.data.remote.SearchPagingSource
import com.example.admissionmeli.dto.Product
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
) : SearchRepository {
    private val searchApi: SearchApi by lazy {
        InitializeNetwork.provideRetrofit().create(SearchApi::class.java)
    }

    override fun getSearchPaging(query: String): Flow<PagingData<Product>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                prefetchDistance = 2
            ),
            pagingSourceFactory = { SearchPagingSource(searchApi, query) }
        ).flow


}