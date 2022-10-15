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

    @SerialName("full_name")
    val full_name: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("owner")
    val owner: Owner? = null,

    @SerialName("visibility")
    val visibility: String? = null,

    @SerialName("private")
    val private: Boolean? = null,

    @SerialName("html_url")
    val html_url: String? = null,

    ) : Parcelable

@Parcelize
data class Owner(
    @SerialName("avatar_url")
    val avatar_url: String? = null
) : Parcelable