package com.example.trendingproject.details.model

data class TrendingProjectDetailsUiModel(
    val name: String,
    val description: String,
    val ownerName: String,
    val language: String?,
    val watchersCount: Int,
    val forksCount: Int,
    val isFavorite: Boolean
)