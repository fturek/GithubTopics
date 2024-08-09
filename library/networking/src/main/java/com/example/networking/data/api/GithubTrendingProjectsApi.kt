package com.example.networking.data.api

import com.example.networking.data.model.GetTrendingProjectResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GithubTrendingProjectsApi {

    @Headers("Accept: application/vnd.github.preview")
    @GET("search/repositories")
    suspend fun getTrendingProjects(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): GetTrendingProjectResponse
}