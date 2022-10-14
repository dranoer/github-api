package com.dranoer.abnamro.data.remote

import com.dranoer.abnamro.data.model.Repo
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("repos")
    suspend fun getRepoList(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int,
    ): List<Repo>
}