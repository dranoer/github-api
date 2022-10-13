package com.dranoer.abnamro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dranoer.abnamro.ui.screen.ListScreen
import com.dranoer.abnamro.ui.theme.AbnamroTheme
import com.dranoer.abnamro.ui.util.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AbnamroTheme {
                AppScreen()
            }
        }
    }
}

@Composable
fun AppScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.List.route,
    ) {
        composable(route = Route.List.route) {
            ListScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AbnamroTheme {
        AppScreen()
    }
}