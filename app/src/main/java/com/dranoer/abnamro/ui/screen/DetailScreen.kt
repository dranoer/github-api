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
import androidx.compose.ui.res.stringResource
import com.dranoer.abnamro.data.model.RepoEntity

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

@Composable
private fun DetailContent(
    modifier: Modifier = Modifier,
    viewState: RepoEntity,
    backPress: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id = R.string.detail)) }, navigationIcon = {
                IconButton(onClick = { backPress() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.app_name),
                        tint = colorResource(id = R.color.white)
                    )
                }
            })
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
            ) {
                DetailItem(item = viewState)
            }
        }
    )
}