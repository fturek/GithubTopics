package com.example.trendingproject.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networking.domain.usecase.AddTrendingProjectToFavouritesUseCase
import com.example.networking.domain.usecase.GetTrendingProjectsUseCase
import com.example.networking.domain.usecase.RemoveTrendingProjectFromFavouritesUseCase
import com.example.trendingproject.list.mapper.TrendingProjectMapper
import com.example.trendingproject.list.model.TrendingProjectUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TrendingProjectListViewModel @Inject constructor(
    private val trendingProjectMapper: TrendingProjectMapper,
    private val getTrendingProjectsUseCase: GetTrendingProjectsUseCase,
    private val addTrendingProjectToFavouritesUseCase: AddTrendingProjectToFavouritesUseCase,
    private val removeTrendingProjectFromFavouritesUseCase: RemoveTrendingProjectFromFavouritesUseCase
) : ViewModel() {

    private val _trendingUiModels = MutableLiveData<List<TrendingProjectUiModel>>()
    val trendingUiModels: LiveData<List<TrendingProjectUiModel>> = _trendingUiModels

    private val _showEmptyState = MutableLiveData<Boolean>()
    val showEmptyState: LiveData<Boolean> = _showEmptyState

    private val _showErrorState = MutableLiveData<Boolean>()
    val showErrorState: LiveData<Boolean> = _showErrorState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchTrendingProjects()
    }

    fun fetchTrendingProjects() = viewModelScope.launch {
        _showErrorState.value = false
        _isLoading.value = true
        getTrendingProjectsUseCase(Unit)
            .onSuccess { trendingProjects ->
                _isLoading.value = false
                if (trendingProjects.isEmpty()) {
                    _showEmptyState.value = true
                    return@onSuccess
                }
                _showEmptyState.value = false
                _trendingUiModels.value = trendingProjectMapper.mapToUiModels(trendingProjects)
            }
            .onFailure {
                Timber.e("Error fetching trending projects: $it")
                _isLoading.value = false
                _showErrorState.value = true
            }
    }

    fun onFavoriteClicked(trendingProjectId: Int, isFavorite: Boolean) = viewModelScope.launch {
        // It is written stupidly, dont have enough time.
        // Whole object should be stored in database, and it shouldn't be updated on ui list.
        if (isFavorite) {
            removeTrendingProjectFromFavouritesUseCase(trendingProjectId)
                .onSuccess {
                    _trendingUiModels.value = _trendingUiModels.value?.map {
                        if (it.id == trendingProjectId) {
                            it.copy(isFavorite = false)
                        } else {
                            it
                        }
                    }
                }
        } else {
            addTrendingProjectToFavouritesUseCase(trendingProjectId)
                .onSuccess {
                    _trendingUiModels.value = _trendingUiModels.value?.map {
                        if (it.id == trendingProjectId) {
                            it.copy(isFavorite = true)
                        } else {
                            it
                        }
                    }
                }
        }
    }
}