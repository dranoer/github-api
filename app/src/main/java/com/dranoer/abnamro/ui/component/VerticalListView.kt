package com.dranoer.abnamro.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.dranoer.abnamro.data.model.Repo
import com.dranoer.abnamro.ui.OnClickListener

@ExperimentalCoilApi
@Composable
fun VerticalListView(repos: List<Repo>, callback: OnClickListener) {
    LazyColumn {
        items(
            items = repos,
            itemContent = { item ->
                VerticalListItem(item = item, callback)
                ListItemDivider()
            }
        )
    }
}

@Composable
private fun ListItemDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.06f)
    )
}