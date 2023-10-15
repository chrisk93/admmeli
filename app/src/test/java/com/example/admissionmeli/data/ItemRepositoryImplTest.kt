package com.example.admissionmeli.data

import com.example.admissionmeli.data.remote.ItemApi
import com.example.admissionmeli.dto.remote.ProductDescriptionResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import retrofit2.Response

class ItemRepositoryImplTest {

    private lateinit var itemRepository: ItemRepositoryImpl
    private lateinit var itemApi: ItemApi

    @Before
    fun setUp() {
        itemRepository = spyk()
        itemApi = mockk(relaxed = true)
        every { itemRepository.getProperty("itemApi") } returns itemApi
    }

    @Test
    fun `getProductDescription returns a success response`() = runBlocking {
        val idItem = "idItem"

        coEvery { itemApi.getProductDescription(idItem) } returns Response.success(
            ProductDescriptionResponse(
                description = "plain_text"
            )
        )

        val result = itemRepository.getProductDescription(idItem)

        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals("plain_text", result.getOrNull()?.description)

        coVerify { itemApi.getProductDescription(idItem) }
    }

    @Test
    fun `getProductDescription returns a failure response`() = runBlocking {
        val idItem = "12"
        val error = Response.error<ProductDescriptionResponse>(
            404,
            "Not Found".toResponseBody()
        )

        coEvery { itemApi.getProductDescription(idItem) } returns error

        val result = itemRepository.getProductDescription(idItem)

        Assert.assertTrue(result.isFailure)
        coVerify { itemApi.getProductDescription(idItem) }
    }
}