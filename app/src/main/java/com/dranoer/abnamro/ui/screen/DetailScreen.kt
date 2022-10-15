package com.dranoer.abnamro.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dranoer.abnamro.R
import com.dranoer.abnamro.ui.MainViewModel
import com.dranoer.abnamro.ui.component.DetailItem
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.collectAsState
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun DetailContent(
    modifier: Modifier = Modifier,
    viewState: RepoEntity,
    backPress: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(modifier = Modifier.padding(4.dp)) {
                        Text(text = stringResource(id = R.string.app_name))
                    }
                },
                elevation = 8.dp,
            )
        },
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                DetailItem(item = viewState)
            }
        }
    )
}