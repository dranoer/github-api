package com.dranoer.abnamro.ui.screen

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.dranoer.abnamro.R
import com.dranoer.abnamro.ui.MainViewModel
import com.dranoer.abnamro.ui.OnClickListener
import com.dranoer.abnamro.ui.component.ErrorView
import com.dranoer.abnamro.ui.component.RepoListView
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
                    Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.size_4))) {
                        Text(text = stringResource(id = R.string.app_name))
                    }
                },
            )
        },
        content = { padding ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(colorResource(id = R.color.light_blue)),
            ) {
                when (viewState) {
                    is ViewState.Loading -> CircularProgressIndicator()
                    is ViewState.Success -> {
                        val isRefreshing by viewModel.isRefreshing.collectAsState()
                        SwipeRefresh(
                            state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                            onRefresh = { viewModel.refresh() },
                        ) {
                            RepoListView(
                                viewState.data,
                                OnClickListener { repo -> onClickToDetailScreen.invoke(repo.id) }
                            )
                        }
                    }
                    is ViewState.Error ->
                        ErrorView(message = viewState.message, viewModel::refresh)
                }
            }
        }
    )
}