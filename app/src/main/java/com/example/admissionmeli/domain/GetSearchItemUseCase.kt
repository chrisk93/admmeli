package com.example.admissionmeli.domain

import com.example.admissionmeli.data.SearchRepository
import javax.inject.Inject

class GetSearchItemUseCase @Inject constructor(private val searchRepository: SearchRepository) {
    operator fun invoke(query: String) = searchRepository.getSearchPaging(query)
}