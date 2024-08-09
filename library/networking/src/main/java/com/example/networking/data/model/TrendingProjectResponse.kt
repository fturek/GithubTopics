package com.example.networking.data.model

import com.google.gson.annotations.SerializedName

data class TrendingProjectResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    @SerializedName("language")
    val language: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val ownerName: Owner,
    @SerializedName("forks")
    val forks: Int
)

data class Owner(
    @SerializedName("login")
    val login: String
)