package com.dranoer.abnamro.ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dranoer.abnamro.ui.MainViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {
    DetailContent()
}

@Composable
private fun DetailContent(
) {
    Text(text = "detail")
}