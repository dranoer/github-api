package com.dranoer.abnamro.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Repo(
    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String,

    @SerialName("owner")
    val owner: Owner,

    @SerialName("private")
    val private: Boolean,

    @SerialName("visibility")
    val visibility: String,
) : Parcelable

@Parcelize
data class Owner(
    @SerialName("avatar_url")
    val avatar_url: String
) : Parcelable