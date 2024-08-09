package com.example.networking.data.datasource

import com.example.networking.data.api.GithubTrendingProjectsApi
import com.example.networking.data.mapper.toDomain
import com.example.networking.domain.model.TrendingProject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingProjectsRemoteDataSource @Inject constructor(
    private val api: GithubTrendingProjectsApi
) {
    suspend fun getTrendingProjects(): List<TrendingProject> {
        val trendingProjectResponseList = api.getTrendingProjects(
            query = QUERY,
            sort = SORT,
            order = ORDER,
            page = PAGE,
            perPage = PER_PAGE
        ).items

        return trendingProjectResponseList.map { it.toDomain() }
    }


    companion object {
        private const val QUERY = "stars:>=1"
        private const val SORT = "stars"
        private const val ORDER = "desc"
        private const val PAGE = 1
        private const val PER_PAGE = 20
    }
}