package com.dranoer.abnamro.data.model

import com.google.gson.annotations.SerializedName

data class Repo(
    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("private")
    val private: Boolean,

    @field:SerializedName("visibility")
    val visibility: String,
)