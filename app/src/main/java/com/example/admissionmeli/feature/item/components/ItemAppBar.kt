package com.example.admissionmeli.feature.item.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.admissionmeli.ui.theme.AdmissionmeliTheme
import com.example.admissionmeli.ui.theme.brandColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemAppBar(

    title: String = "",
    onBackPressed: () -> Unit,
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Arrow Icon"
                )
            }
        },
        title = {
            Text(text = title, fontSize = MaterialTheme.typography.titleMedium.fontSize)
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.brandColor,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.Black,
        )
    )
}





@Preview(showBackground = true)
@Composable
fun ItemAppBarPreview() {

    fun emptyFunc() {}

    AdmissionmeliTheme {
        ItemAppBar("Android", ::emptyFunc)

    }
}