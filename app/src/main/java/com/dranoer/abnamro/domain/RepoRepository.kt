package com.dranoer.abnamro.domain

import com.dranoer.abnamro.data.model.Repo
import com.dranoer.abnamro.data.remote.NetworkDataSource
import com.dranoer.abnamro.data.remote.Resource
import javax.inject.Inject

class RepoRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) {
    suspend fun fetchRepo(): Resource<List<Repo>> {
        return networkDataSource.getRepos()
    }
}