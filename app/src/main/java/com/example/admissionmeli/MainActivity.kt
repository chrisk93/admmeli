package com.example.admissionmeli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.admissionmeli.navigation.Screens
import com.example.admissionmeli.navigation.AppNavGraph
import com.example.admissionmeli.ui.theme.AdmissionmeliTheme
import com.example.admissionmeli.ui.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdmissionmeliTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setStatusBarColor(color = MaterialTheme.colorScheme.statusBarColor)

                val navController = rememberNavController()
                AppNavGraph(
                    startDestination = Screens.Search.route,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AdmissionmeliTheme {
        Greeting("Android")
    }
}