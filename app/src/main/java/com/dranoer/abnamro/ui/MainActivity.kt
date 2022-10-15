package com.dranoer.abnamro.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dranoer.abnamro.ui.screen.DetailScreen
import com.dranoer.abnamro.ui.screen.ListScreen
import com.dranoer.abnamro.ui.theme.AbnamroTheme
import com.dranoer.abnamro.ui.util.Const.DETAIL_ARG_REPO_ID
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
            ListScreen(
                onClickToDetailScreen = { repoId ->
                    navController.navigate(
                        Route.Detail.createRoute(repoId)
                    )
                }
            )
        }
        composable(
            route = Route.Detail.route,
            arguments = listOf(
                navArgument(DETAIL_ARG_REPO_ID) {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val repoId = backStackEntry.arguments?.getLong(DETAIL_ARG_REPO_ID)
            requireNotNull(repoId) { "repo id wasn't found!" }
            DetailScreen(
                id = repoId,
                backPress = {
                    navController.navigateUp()
                }
            )
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