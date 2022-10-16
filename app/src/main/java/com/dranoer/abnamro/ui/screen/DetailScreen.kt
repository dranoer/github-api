package com.dranoer.abnamro.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dranoer.abnamro.R
import com.dranoer.abnamro.ui.MainViewModel
import com.dranoer.abnamro.ui.component.DetailItem
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import com.dranoer.abnamro.data.model.RepoEntity
import com.dranoer.abnamro.ui.theme.GithubRepoTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    backPress: () -> Unit,
    id: Long = -1,
) {

    fun launch() {
        viewModel.getDetail(id)
    }

    launch()

    DetailContent(
        modifier = modifier.padding(8.dp),
        viewState = viewModel.detailFlow.collectAsState().value,
        backPress = backPress,
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun DetailContent(
    modifier: Modifier = Modifier,
    viewState: RepoEntity,
    backPress: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { backPress() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.app_name),
                            tint = colorResource(id = R.color.dark_blue)
                        )
                    }
                },
                backgroundColor = colorResource(id = R.color.white),
                elevation = 0.dp,
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = dimensionResource(id = R.dimen.size_40),
                        top = dimensionResource(id = R.dimen.size_10),
                        end = dimensionResource(id = R.dimen.size_40),
                        bottom = dimensionResource(id = R.dimen.size_10)
                    ),
            ) {
                DetailItem(item = viewState)
            }
        }
    )
}

//region Preview
@Preview("Normal detail screen")
@Composable
private fun PreviewDetailScreen_Normal() {
    GithubRepoTheme {
        DetailContent(
            viewState = RepoEntity(
                id = 12345,
                name = "Normal name",
                full_name = "Normal preview fullname",
                description = "This is normal preview description.",
                avatar_url = "",
                visibility = "private",
                private = false,
            )
        )
    }
}
//endregion