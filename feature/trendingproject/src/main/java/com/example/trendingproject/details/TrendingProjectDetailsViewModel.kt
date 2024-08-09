package com.example.trendingproject.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networking.domain.usecase.GetTrendingProjectDetailsUseCase
import com.example.trendingproject.details.mapper.TrendingProjectDetailsMapper
import com.example.trendingproject.details.model.TrendingProjectDetailsUiModel
import com.example.trendingproject.list.model.TrendingProjectUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TrendingProjectDetailsViewModel @Inject constructor(
    private val trendingProjectDetailsMapper: TrendingProjectDetailsMapper,
    private val getTrendingProjectDetailsUseCase: GetTrendingProjectDetailsUseCase
) : ViewModel() {

    private val _trendingProjectDetails = MutableLiveData<TrendingProjectDetailsUiModel>()
    val trendingProjectDetails: LiveData<TrendingProjectDetailsUiModel> = _trendingProjectDetails

    private val _showErrorState = MutableLiveData<Boolean>()
    val showErrorState: LiveData<Boolean> = _showErrorState

    fun getTrendingProjectDetails(trendingProjectId: Int) = viewModelScope.launch {
        _showErrorState.value = false
        getTrendingProjectDetailsUseCase(trendingProjectId)
            .onSuccess { trendingProjectDetails ->
                _trendingProjectDetails.value =
                    trendingProjectDetailsMapper.mapToUiModel(trendingProjectDetails)
            }
            .onFailure {
                Timber.e("Error fetching trending project details: $it")
                _showErrorState.value = true
            }
    }
}