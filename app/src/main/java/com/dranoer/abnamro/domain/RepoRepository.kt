package com.dranoer.abnamro.domain

import android.util.Log
import com.dranoer.abnamro.data.local.RepoDao
import com.dranoer.abnamro.data.model.Repo
import com.dranoer.abnamro.data.model.RepoEntity
import com.dranoer.abnamro.data.model.asDomainModel
import com.dranoer.abnamro.data.remote.WebService
import com.dranoer.abnamro.ui.util.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RepoRepository @Inject constructor(
    private val webService: WebService,
    private val dao: RepoDao,
) {

    private suspend fun getRepos(): List<Repo> = webService.getRepoList(page = 1, pageSize = 10)

    private suspend fun queryRepos(): List<Repo> = dao.getRepos().asDomainModel()

    private suspend fun saveFetchResult(items: List<Repo>) {
        for (item in items) {
            dao.insertAll(
                RepoEntity(
                    id = item.id,
                    name = item.name ?: "",
                    full_name = item.full_name ?: "",
                    description = item.description ?: "",
                    avatar_url = item.owner?.avatar_url ?: "",
                    visibility = item.visibility ?: "",
                    private = item.private ?: false,
                    html_url = item.html_url ?: "",
                )
            )
        }
    }

    suspend fun getDetail(id: Long): RepoEntity {
        return dao.getDetail(id)
    }

    val result: Flow<ViewState<List<Repo>>> = flow {
        emit(ViewState.Loading)
        val cache = queryRepos()
        if (cache.isNotEmpty()) {
            // step 1: View cache
            emit(ViewState.Success(cache))
            try {
                // step 2: Make network call, save result to the cache
                refresh()
                // step 3: View cash
                emit(ViewState.Success(queryRepos()))
            } catch (t: Throwable) {
                Log.d("RepoRepository", t.localizedMessage ?: "Failed fetch")
            }
        } else {
            try {
                // step 1: Make network call, save result to the cache
                refresh()
                // step 2: View cash
                emit(ViewState.Success(queryRepos()))
            } catch (t: Throwable) {
                emit(ViewState.Error("Failed refresh"))
            }
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun refresh() {
        saveFetchResult(getRepos())
    }
}