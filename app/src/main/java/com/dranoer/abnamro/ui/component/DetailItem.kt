package com.dranoer.abnamro.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.dranoer.abnamro.R
import com.dranoer.abnamro.data.model.RepoEntity

@ExperimentalCoilApi
@Composable
fun DetailItem(item: RepoEntity) {
    val privacy: String =
        if (item.private) stringResource(R.string.is_true) else stringResource(R.string.is_false)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            modifier = Modifier.size(50.dp),
            painter = rememberAsyncImagePainter(item.avatar_url),
            contentDescription = null,
        )
        Text(
            text = item.name,
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = stringResource(R.string.privacy_state, privacy),
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = stringResource(R.string.visibility_state, item.visibility),
            style = MaterialTheme.typography.subtitle2
        )
    }
}