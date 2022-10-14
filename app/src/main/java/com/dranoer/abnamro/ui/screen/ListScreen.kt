package com.dranoer.abnamro.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import com.dranoer.abnamro.R
import com.dranoer.abnamro.data.model.Repo
import com.dranoer.abnamro.ui.theme.AbnamroTheme
import com.dranoer.abnamro.ui.viewmodel.RepoViewModel

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: RepoViewModel = hiltViewModel(),
) {
    ListContent(
        modifier = modifier.padding(8.dp),
        repoList = viewModel.repos,
        name = "temp",
        visibility = "publiC"
    )
}

@Composable
private fun ListContent(
    modifier: Modifier = Modifier,
    repoList: LiveData<List<Repo>>? = null,
    name: String = "",
    imageUrl: String = "",
    visibility: String = "",
    isPublic: Boolean = true,
) {
    Card {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null
            )
            Text(text = name)
            Text(text = visibility)
            Text(text = if (isPublic) "Public" else "Private")
        }
    }
    Text(text = name)
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