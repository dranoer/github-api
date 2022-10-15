package com.dranoer.abnamro.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dranoer.abnamro.R
import com.dranoer.abnamro.ui.MainViewModel
import com.dranoer.abnamro.ui.OnClickListener
import com.dranoer.abnamro.ui.component.ErrorView
import com.dranoer.abnamro.ui.component.VerticalListView
import com.dranoer.abnamro.ui.util.ViewState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    onClickToDetailScreen: (Long) -> Unit = {},
) {

    val viewState = viewModel.stateFlow.collectAsState().value

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
        content = { padding ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                when (viewState) {
                    is ViewState.Loading -> CircularProgressIndicator()
                    is ViewState.Success -> {
                        val isRefreshing by viewModel.isRefreshing.collectAsState()
                        SwipeRefresh(
                            state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                            onRefresh = { viewModel.refresh() },
                        ) {
                            VerticalListView(viewState.data, OnClickListener { repo ->
                                onClickToDetailScreen.invoke(repo.id)
                            })
                        }
                        viewModel.finishLoading()
                    }
                    is ViewState.Error ->
                        ErrorView(message = viewState.message, viewModel::refresh)
                }
            }
        }
    )
}