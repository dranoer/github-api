package com.dranoer.abnamro.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import com.dranoer.abnamro.R
import com.dranoer.abnamro.data.model.Owner
import com.dranoer.abnamro.data.model.Repo
import com.dranoer.abnamro.ui.OnClickListener
import com.dranoer.abnamro.ui.theme.GithubRepoTheme

@ExperimentalCoilApi
@Composable
fun RepoListView(repos: List<Repo>, callback: OnClickListener?) {
    LazyColumn(
        modifier = Modifier.padding(
            start = dimensionResource(id = R.dimen.size_18),
            top = dimensionResource(id = R.dimen.size_12),
            end = dimensionResource(id = R.dimen.size_18)
        ),
    ) {
        items(
            items = repos,
            itemContent = { item ->
                RepoItem(item = item, callback)
            }
        )
    }
}

//region Preview
@Preview("Normal repo list")
@Composable
private fun PreviewRepoList_Normal() {

    val repo = Repo(
        id = 12345,
        name = "Normal preview name",
        owner = Owner(""),
        visibility = "Visible",
        private = true,
    )

    GithubRepoTheme {
        RepoListView(
            repos = listOf(repo),
            callback = null,
        )
    }
} //endregion