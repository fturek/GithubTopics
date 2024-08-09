package com.example.trendingproject.details.mapper

import com.example.networking.domain.model.TrendingProject
import com.example.trendingproject.details.model.TrendingProjectDetailsUiModel
import javax.inject.Inject

class TrendingProjectDetailsMapper @Inject constructor() {

    fun mapToUiModel(trendingProject: TrendingProject) =
        TrendingProjectDetailsUiModel(
            name = trendingProject.name,
            description = trendingProject.description.orEmpty(),
            ownerName = trendingProject.ownerName,
            language = trendingProject.language,
            watchersCount = trendingProject.watchersCount,
            forksCount = trendingProject.forksCount,
            isFavorite = trendingProject.isFavourite
        )
}