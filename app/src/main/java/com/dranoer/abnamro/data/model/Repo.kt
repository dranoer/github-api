package com.dranoer.abnamro.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Repo(
    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String? = null,

    @SerialName("owner")
    val owner: Owner? = null,

    @SerialName("private")
    val private: Boolean? = null,

    @SerialName("visibility")
    val visibility: String? = null,
) : Parcelable

@Parcelize
data class Owner(
    @SerialName("avatar_url")
    val avatar_url: String? = null
) : Parcelable