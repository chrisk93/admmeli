package com.example.admissionmeli.data.remote

import com.example.admissionmeli.dto.remote.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {
    @GET("/sites/{siteId}/search")
    suspend fun searchProducts(
        @Path("siteId") siteId: String = "MCO",
        @Query("q") query: String,
        @Query("limit") limit: String = "10",
        @Query("offset") offset: Int = 0,
    ): Response<SearchResponse>
}