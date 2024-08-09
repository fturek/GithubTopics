package com.example.trendingproject.list.mapper

import com.example.networking.domain.model.TrendingProject
import com.example.trendingproject.list.model.TrendingProjectUiModel
import javax.inject.Inject

class TrendingProjectMapper @Inject constructor() {

    fun mapToUiModels(trendingProjects: List<TrendingProject>) =
        trendingProjects.map { mapToUiModel(it) }

    private fun mapToUiModel(trendingProject: TrendingProject) =
        TrendingProjectUiModel(
            id = trendingProject.id,
            name = trendingProject.name,
            description = trendingProject.description.orEmpty(),
            isFavorite = trendingProject.isFavourite
        )
}