package com.example.admissionmeli.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.admissionmeli.R
import com.example.admissionmeli.core.utils.toLocalCurrency
import com.example.admissionmeli.core.components.SearchToolbar
import com.example.admissionmeli.core.network.getErrorMessage
import com.example.admissionmeli.core.ui.components.IconText
import com.example.admissionmeli.dto.Product

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    state: SearchResultUiState = SearchResultUiState.Loading,
    searchResults: LazyPagingItems<Product>,
    onSearchQueryChanged: (String) -> Unit = {},
    onSearchQuerySubmit: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
    onSearchItemClick: (Product) -> Unit = {},
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        SearchToolbar(
            onBackClick = onBackClick,
            onSearchQueryChanged = onSearchQueryChanged,
            searchQuery = searchQuery,
            onSearchTriggered = onSearchQuerySubmit
        )

        when (state) {
            SearchResultUiState.EmptyValue -> {
                IconText(
                    text = stringResource(id  = R.string.looking_for),
                    image = R.drawable.cart
                )
            }

            SearchResultUiState.Loading -> {
                LoadingView()
            }

            SearchResultUiState.Loaded -> {
                SearchContentPagination(
                    searchResults = searchResults,
                    onItemClick = onSearchItemClick
                )
            }
        }
    }
}

@Composable
fun SearchContentPagination(
    searchResults: LazyPagingItems<Product>,
    onItemClick: (Product) -> Unit,
) {
    when (val state = searchResults.loadState.refresh) {
        is LoadState.Error -> {
            IconText(text = stringResource(id = state.error.getErrorMessage()))
        }

        LoadState.Loading -> {
            LoadingView()
        }

        is LoadState.NotLoading -> Unit
    }

    LazyColumn {
        items(searchResults.itemCount) { index ->
            val item = searchResults[index]
            item?.let {
                SearchItem(
                    modifier = Modifier.clickable {
                        onItemClick(item)
                    },
                    product = item
                )
            }
        }

        item {
            LoadingItems()
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

@Composable
private fun SearchItem(
    modifier: Modifier = Modifier,
    product: Product,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(Shapes().small)
                .size(100.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer),
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.thumbnail)
                .crossfade(true)
                .build(),
            contentDescription = "Gallery Image",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleSmall,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2
            )
            Text(
                text = product.price.toLocalCurrency(),
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = stringResource(
                    id = R.string.available_quantity,
                    product.availableQuantity
                ),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Light
            )

        }
    }
}

@Composable
fun LoadingItems() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    SearchItem(
        product = Product(
            id = "123",
            title = "title",
            price = 500.0,
            thumbnail = "url",
            availableQuantity = 1,
            soldQuantity = 1,
            condition = "new",
            currencyId = "ARS"
        )
    )
}