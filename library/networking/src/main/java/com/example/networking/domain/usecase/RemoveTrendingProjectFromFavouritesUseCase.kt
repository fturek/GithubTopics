package com.example.networking.domain.usecase

import com.example.base.UseCase
import com.example.networking.TrendingProjectRepository
import javax.inject.Inject

class RemoveTrendingProjectFromFavouritesUseCase @Inject constructor(
    private val repository: TrendingProjectRepository
) : UseCase<Int, Result<Unit>> {

    override suspend fun invoke(input: Int) =
        runCatching { repository.removeTrendingProjectFromFavourites(input) }
            .fold(
                onSuccess = { Result.success(it) },
                onFailure = { Result.failure(it) }
            )
}