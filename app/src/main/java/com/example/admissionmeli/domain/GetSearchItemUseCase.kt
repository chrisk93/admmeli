package com.example.admissionmeli.domain

import com.example.admissionmeli.data.SearchRepository
import com.example.admissionmeli.data.SearchRepositoryImpl

class GetSearchItemUseCase() {
    private val searchRepository: SearchRepository by lazy { SearchRepositoryImpl() }
    operator fun invoke(query: String) = searchRepository.getSearchPaging(query)
}