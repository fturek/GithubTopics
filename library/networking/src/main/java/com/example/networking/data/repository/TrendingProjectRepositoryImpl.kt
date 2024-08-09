package com.example.networking.data.repository

import com.example.networking.TrendingProjectRepository
import com.example.networking.data.datasource.FavouriteTrendingProjectsDataBaseDataSource
import com.example.networking.data.datasource.TrendingProjectsCacheDataSource
import com.example.networking.data.datasource.TrendingProjectsRemoteDataSource
import com.example.networking.domain.model.TrendingProject
import timber.log.Timber
import javax.inject.Inject

internal class TrendingProjectRepositoryImpl @Inject constructor(
    private val remoteDataSource: TrendingProjectsRemoteDataSource,
    private val cacheDataSource: TrendingProjectsCacheDataSource,
    private val dbDataSource: FavouriteTrendingProjectsDataBaseDataSource,
) : TrendingProjectRepository {

    override suspend fun getTrendingProjects(): List<TrendingProject> {
        val trendingProjects = remoteDataSource.getTrendingProjects()
        cacheDataSource.cache(trendingProjects)
        val favouriteTrendingProjectsid = dbDataSource.getFavouriteTrendingProjectsIds()
        Timber.e("FT, Favourite trending projects: $favouriteTrendingProjectsid")

        return trendingProjects.map {
            if (!favouriteTrendingProjectsid.contains(it.id)) {
                it.copy(isFavourite = false)
            } else {
                it.copy(isFavourite = true)
            }
        }
    }

    override suspend fun getTrendingProjectDetails(trendingProjectId: Int): TrendingProject {
        val trendingProjectDetails =
            cacheDataSource.getTrendingProjects().find { it.id == trendingProjectId }
        if (trendingProjectDetails == null) {
            throw IllegalArgumentException("Trending project with id $trendingProjectId not found")
        } else {
            return trendingProjectDetails
        }
    }

    override suspend fun addTrendingProjectToFavourites(trendingProjectId: Int) {
        dbDataSource.setFavouriteTrendingProject(trendingProjectId)
    }

    override suspend fun removeTrendingProjectFromFavourites(trendingProjectId: Int) {
        dbDataSource.removeFavouriteTrendingProject(trendingProjectId)
    }
}