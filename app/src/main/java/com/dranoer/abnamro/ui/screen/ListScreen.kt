package com.dranoer.abnamro.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dranoer.abnamro.ui.theme.AbnamroTheme

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        ListContent(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun ListContent(
    modifier: Modifier = Modifier,
) {
}

//region Preview
@Preview()
@Composable
fun ListScreenPreview() {
    AbnamroTheme {
        ListContent()
    }
}
//endregion