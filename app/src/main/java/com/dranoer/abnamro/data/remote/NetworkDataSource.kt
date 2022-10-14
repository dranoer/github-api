package com.dranoer.abnamro.data.remote

import com.dranoer.abnamro.data.model.Repo
import com.dranoer.abnamro.ui.util.Const
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val webService: WebService,
) {
    suspend fun getRepos(): Resource<List<Repo>> {
        return Resource.Success(
            webService.getRepos(
                page = 1,
                pageSize = Const.PAGE_SIZE
            )
        )
    }
}