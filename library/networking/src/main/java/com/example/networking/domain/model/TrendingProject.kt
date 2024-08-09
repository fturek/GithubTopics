package com.example.networking.domain.model

data class TrendingProject(
    val id: Int,
    val htmlUrl: String?,
    val watchersCount: Int,
    val language: String?,
    val description: String?,
    val name: String,
    val ownerName: String,
    val forksCount: Int,
    val isFavourite: Boolean = false
)