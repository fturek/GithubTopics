package com.example.networking.domain.usecase

import com.example.base.UseCase
import com.example.networking.TrendingProjectRepository
import com.example.networking.domain.model.TrendingProject
import javax.inject.Inject

class GetTrendingProjectsUseCase @Inject constructor(
    private val repository: TrendingProjectRepository
) : UseCase<Unit, Result<List<TrendingProject>>> {

    override suspend fun invoke(input: Unit) =
        runCatching { repository.getTrendingProjects() }
            .fold(
                onSuccess = { Result.success(it) },
                onFailure = { Result.failure(it) }
            )
}