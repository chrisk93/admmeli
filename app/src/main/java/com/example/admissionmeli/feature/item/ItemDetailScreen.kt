package com.example.admissionmeli.feature.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.admissionmeli.R
import com.example.admissionmeli.core.ui.components.IconText
import com.example.admissionmeli.feature.item.components.ItemAppBar


@Composable
fun ItemDetailScreen(
    uiState: ItemDetailUiState,
    onNavigateBack: () -> Unit,
) {

    Scaffold(
        topBar = {
            ItemAppBar(
                title = stringResource(id = R.string.item),
                onBackPressed = onNavigateBack
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            when (uiState) {
                ItemDetailUiState.Loading -> {
                    LoadingView()
                }

                is ItemDetailUiState.Success -> {
                    ItemDetailContent(
                        product = uiState.product
                    )
                }

                is ItemDetailUiState.LoadFailed -> {
                    IconText(text = uiState.message.asString())
                }
            }
        }
    }


}


@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}