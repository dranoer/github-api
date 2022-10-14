package com.dranoer.abnamro.domain

import com.dranoer.abnamro.data.model.Repo
import com.dranoer.abnamro.data.remote.WebService
import com.dranoer.abnamro.ui.util.ViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOn

class RepoRepository @Inject constructor(
    private val webService: WebService,
) {

    private suspend fun getRepos(): List<Repo> = webService.getRepoList(page = 1, pageSize = 10)

    val result: Flow<ViewState<List<Repo>>> = flow {
        emit(ViewState.Loading)
        try {
            emit(ViewState.Success(getRepos()))
        } catch (t: Throwable) {
            // ToDo
        }
    }.flowOn(Dispatchers.IO)
}