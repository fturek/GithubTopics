package com.example.networking.domain.usecase

import com.example.base.UseCase
import com.example.networking.TrendingProjectRepository
import com.example.networking.domain.model.TrendingProject
import javax.inject.Inject

class GetTrendingProjectDetailsUseCase @Inject constructor(
    private val repository: TrendingProjectRepository
) : UseCase<Int, Result<TrendingProject>> {

    override suspend fun invoke(input: Int) =
        runCatching { repository.getTrendingProjectDetails(input) }
            .fold(
                onSuccess = { Result.success(it) },
                onFailure = { Result.failure(it) }
            )
}