package com.example.admissionmeli.data

import androidx.paging.PagingSource
import com.example.admissionmeli.data.remote.SearchApi
import com.example.admissionmeli.data.remote.SearchPagingSource
import com.example.admissionmeli.dto.Product
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class SearchRepositoryImplTest  {

    private lateinit var searchRepositoryImpl: SearchRepositoryImpl
    private lateinit var searchApi: SearchApi

    @Before
    fun setUp() {
        searchRepositoryImpl = spyk()
        searchApi = mockk(relaxed = true)
        every { searchRepositoryImpl.getProperty("searchApi") } returns searchApi
    }

    @Test
    fun `when getSearchPagingSource verify the call to api`(): Unit = runBlocking {
        val searchPagingSource: SearchPagingSource = mockk()
        coEvery { searchPagingSource.load(any()) } returns PagingSource.LoadResult.Page(
            data = emptyList(),
            prevKey = null,
            nextKey = null
        )

        searchRepositoryImpl.getSearchPaging("query")

        coEvery { searchApi.searchProducts(query = "query", offset = 1) }
    }

    @Test
    fun `getSearchPagingSource return error`(): Unit = runBlocking {
        val searchPagingSource: SearchPagingSource = mockk()
        val exception = Exception("error")

        coEvery { searchPagingSource.load(any()) } returns PagingSource.LoadResult.Error(exception)

        searchRepositoryImpl.getSearchPaging("query")
        val loadResult = searchPagingSource.load(PagingSource.LoadParams.Refresh(1, 1, false))

        Assert.assertEquals(
            PagingSource.LoadResult.Error<Any, Any>(throwable = exception),
            loadResult
        )
    }

    @Test
    fun `getSearchPagingSource returns empty PagingData`(): Unit = runBlocking {
        val searchPagingSource: SearchPagingSource = mockk()
        coEvery { searchPagingSource.load(any()) } returns PagingSource.LoadResult.Page(
            data = emptyList(),
            prevKey = null,
            nextKey = null
        )

        searchRepositoryImpl.getSearchPaging("query")
        val loadResult = searchPagingSource.load(PagingSource.LoadParams.Refresh(1, 1, false))

        Assert.assertEquals(
            PagingSource.LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null
            ),
            loadResult
        )
    }

    @Test
    fun `getSearchPagingSource returns PagingData with 1 item`(): Unit = runBlocking {
        val searchPagingSource: SearchPagingSource = mockk()
        val product = Product(
            id = "id",
            title = "title",
            thumbnail = "thumbnail",
            price = 25.0,
            availableQuantity = 1,
            soldQuantity = 5,
            condition = "new",
            currencyId = "ARS",
        )

        coEvery { searchPagingSource.load(any()) } returns PagingSource.LoadResult.Page(
            data = listOf(product),
            prevKey = null,
            nextKey = null
        )

        searchRepositoryImpl.getSearchPaging("query")
        val loadResult = searchPagingSource.load(PagingSource.LoadParams.Refresh(1, 1, false))

        assertEquals(
            PagingSource.LoadResult.Page(
                data = listOf(product),
                prevKey = null,
                nextKey = null
            ),
            loadResult
        )
    }

}