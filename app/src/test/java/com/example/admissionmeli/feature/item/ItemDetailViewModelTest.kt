package com.example.admissionmeli.feature.item

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.admissionmeli.navigation.Screens
import com.example.admissionmeli.domain.GetItemDescriptionUseCase
import com.example.admissionmeli.dto.ItemDetail
import com.example.admissionmeli.dto.ProductDescription
import io.mockk.coEvery
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ItemDetailViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: ItemDetailViewModel
    private var getItemDescriptionUseCase: GetItemDescriptionUseCase = mockk(relaxed = true)
    private var savedStateHandle = SavedStateHandle(
        mapOf(
            Screens.Detail.ITEM_ID_NAV_KEY to "MCO3735"
        )
    )

    @Before
    fun setUp() {
        coEvery { getItemDescriptionUseCase.invoke(any()) } returns Result.success(
            ProductDescription(description = "desc")
        )
        viewModel = spyk(ItemDetailViewModel(savedStateHandle))
        every { viewModel.getProperty("getItemDescriptionUseCase") } returns getItemDescriptionUseCase
    }

    @Test
    fun `test initial state in screen with empty keyHandle`() = runBlocking {
        viewModel = ItemDetailViewModel(mockk())
        viewModel.uiState.test {
            val state = awaitItem()
            Assert.assertTrue(state is ItemDetailUiState.LoadFailed)
        }
    }

    @Test
    fun `test initial state in screen with keyHandle`() = runBlocking {
        viewModel.uiState.test {
            val initialState = awaitItem()
            Assert.assertTrue(initialState is ItemDetailUiState.Loading)
        }
    }

    @Test
    fun `test initial state in screen with keyHandle and success`() = runBlocking {
        viewModel.uiState.test {
            val initialState = awaitItem()
            Assert.assertTrue(initialState is ItemDetailUiState.Loading)

            viewModel.onItemDetailReceived(itemDetail)

            val stateLoaded = awaitItem()
            Assert.assertTrue(stateLoaded is ItemDetailUiState.Success)
        }
    }

    private val itemDetail = ItemDetail(
        id = "MCO3735",
        title = "title",
        price = 4400.0,
        thumbnail = "thumbnail",
        availableQuantity = 1,
        soldQuantity = 1,
        condition = "new",
        currencyId = "COP"
    )

}