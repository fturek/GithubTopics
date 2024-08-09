package com.example.networking.data.mapper

import com.example.networking.data.model.TrendingProjectResponse
import com.example.networking.database.favouritetrendingproject.entity.FavouriteTrendingProjectEntity
import com.example.networking.domain.model.TrendingProject

fun TrendingProjectResponse.toDomain(): TrendingProject {
    return TrendingProject(
        id = id,
        htmlUrl = htmlUrl,
        watchersCount = watchersCount,
        language = language,
        description = description,
        name = name,
        ownerName = ownerName.login,
        forksCount = forks
    )
}

fun toEntity(id: Int): FavouriteTrendingProjectEntity {
    return FavouriteTrendingProjectEntity(
        trendingProjectId = id
    )
}