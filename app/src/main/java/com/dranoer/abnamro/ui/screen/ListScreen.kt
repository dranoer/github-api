package com.dranoer.abnamro.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dranoer.abnamro.R
import com.dranoer.abnamro.data.model.Repo
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
    ListContent(
        modifier = modifier.padding(8.dp),
        viewState = viewModel.stateFlow.collectAsState().value,
        isRefreshing = viewModel.isRefreshing.collectAsState().value,
        onRefresh = { viewModel.refresh() },
        onClickToDetailScreen = onClickToDetailScreen,
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ListContent(
    modifier: Modifier = Modifier,
    viewState: ViewState<List<Repo>>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onClickToDetailScreen: (Long) -> Unit = {},
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
                when (viewState) {
                    is ViewState.Loading -> CircularProgressIndicator()
                    is ViewState.Success -> {
                        SwipeRefresh(
                            state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                            onRefresh = { onRefresh },
                        ) {
                            VerticalListView(viewState.data, OnClickListener { repo ->
                                onClickToDetailScreen.invoke(repo.id)
                            })
                        }
                    }
                    is ViewState.Error ->
                        ErrorView(message = viewState.message, onRefresh)
                }
            }
        }
    )
}