package com.dranoer.abnamro.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dranoer.abnamro.data.model.RepoEntity

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg repos: RepoEntity)

    @Query("SELECT * FROM repos")
    suspend fun getRepos(): List<RepoEntity>

    @Query("SELECT * FROM repos WHERE id = :id")
    suspend fun getDetail(id: Long): RepoEntity
}