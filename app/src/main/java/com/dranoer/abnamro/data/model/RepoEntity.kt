package com.dranoer.abnamro.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class RepoEntity(
    @PrimaryKey
    var id : Long = 0,
    var name: String = "",
    var avatar_url: String = "",
    var private: Boolean = true,
    var visibility: String = "",
)

fun List<RepoEntity>.asDomainModel(): List<Repo> {
    return map {
        Repo(
            id = it.id,
            name = it.name,
            owner = Owner(avatar_url = it.avatar_url),
            private = it.private,
            visibility = it.visibility,
        )
    }
}