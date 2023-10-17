package com.example.admissionmeli.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.admissionmeli.data.remote.SearchApi
import com.example.admissionmeli.data.remote.SearchPagingSource
import com.example.admissionmeli.dto.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi
) : SearchRepository {
    /*private val searchApi: SearchApi by lazy {
        InitializeNetwork.provideRetrofit().create(SearchApi::class.java)
    }*/

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