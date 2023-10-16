package com.example.admissionmeli.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.example.admissionmeli.feature.item.navigation.itemDetailNavigation
import com.example.admissionmeli.feature.search.navigation.searchNavigation
import com.example.admissionmeli.dto.toParcelable


@Composable
fun AppNavGraph(
    startDestination: String,
    navController: NavHostController,
) {
    NavHost(
        startDestination = startDestination,
        navController = navController
    ) {
        searchNavigation(
            onNavigateToItemDetail = { item ->
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    Screens.Detail.ITEM_DETAIL_KEY,
                    item.toParcelable()
                )

                navController.navigate(
                    Screens.Detail.passItemId(
                        productId = item.id
                    )
                )
            })

        itemDetailNavigation(
            navController = navController,
            onNavigateBack = {
                navController.popBackStack()
            }
        )
    }
}