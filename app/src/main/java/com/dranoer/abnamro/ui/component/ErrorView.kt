package com.dranoer.abnamro.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dranoer.abnamro.R
import com.dranoer.abnamro.ui.theme.GithubRepoTheme

@Composable
fun ErrorView(message: String, refresh: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(message)
        Spacer(Modifier.height(dimensionResource(id = R.dimen.size_16)))
        Button(onClick = refresh) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

//region Preview
@Preview("Normal Error view")
@Composable
private fun PreviewErrorView_Normal() {
    GithubRepoTheme() {
        ErrorView(
            message = "Oops! there is sth wrong",
            refresh = {}
        )
    }
} //endregion