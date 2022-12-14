package com.dranoer.abnamro.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class RepoEntity(
    @PrimaryKey
    var id: Long = 0,
    var name: String = "",
    var full_name: String = "",
    var description: String = "",
    var avatar_url: String = "",
    var visibility: String = "",
    var private: Boolean = true,
    var html_url: String = "",
)

fun List<RepoEntity>.asDomainModel(): List<Repo> {
    return map {
        Repo(
            id = it.id,
            name = it.name,
            full_name = it.full_name,
            description = it.description,
            owner = Owner(avatar_url = it.avatar_url),
            visibility = it.visibility,
            private = it.private,
            html_url = it.html_url,
        )
    }
}