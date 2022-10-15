package com.dranoer.abnamro.domain

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

    suspend fun getRepos(): List<Repo> = webService.getRepoList(page = 1, pageSize = 10)

    suspend fun queryRepos(): List<Repo> = dao.getRepos().asDomainModel()

    suspend fun saveFetchResult(items: List<Repo>) {
        while (items.isNotEmpty()) {
            for (item in items) {
                dao.insertAll(RepoEntity(
                    id = item.id,
                    name = item.name ?: "mm",
                    avatar_url = item.owner?.avatar_url ?: "pp",
                    private = item.private ?: false,
                    visibility = item.visibility ?: "not viss",
                ))
            }
        }
    }

    fun isNotEmpty(t: List<Repo>): Boolean = t.isNotEmpty()

    val result: Flow<ViewState<List<Repo>>> = flow {
        emit(ViewState.Loading)
        queryRepos().let {
            if (isNotEmpty(it)) {
                emit(ViewState.Success(it))
            }
            try {
                saveFetchResult(getRepos())
                emit(ViewState.Success(queryRepos()))
            } catch (t: Throwable) {
                if (isNotEmpty(it)) {
                    return@flow
                }
                emit(ViewState.Error("Something went wrong"))
            }
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getDetail(id: Long): RepoEntity {
        return dao.getDetail(id)
    }
}