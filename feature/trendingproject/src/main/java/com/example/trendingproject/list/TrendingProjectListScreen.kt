package com.example.trendingproject.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.design.animation.AnimatedStyle
import com.example.design.color.ColorsPalette
import com.example.design.composables.EmptyListView
import com.example.design.composables.ErrorView
import com.example.design.composables.LoadingView
import com.example.trendingproject.TrendingProjectsGraph
import com.example.trendingproject.list.model.TrendingProjectUiModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.parameters.CodeGenVisibility.INTERNAL
import com.ramcosta.composedestinations.generated.trendingproject.destinations.TrendingProjectDetailsScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.localization.R as LocalizationR

@OptIn(ExperimentalMaterial3Api::class)
@Destination<TrendingProjectsGraph>(
    start = true,
    visibility = INTERNAL,
    style = AnimatedStyle.SlideInOut::class
)
@Composable
internal fun TrendingProjectsListScreen(
    navigator: DestinationsNavigator,
    viewModel: TrendingProjectListViewModel = hiltViewModel()
) {
    val trendingProjects by viewModel.trendingUiModels.observeAsState(emptyList())
    val showEmptyState by viewModel.showEmptyState.observeAsState(false)
    val showErrorState by viewModel.showErrorState.observeAsState(false)
    val isLoading by viewModel.isLoading.observeAsState(true)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = LocalizationR.string.trending_projects_top_appbar_name))
                }
            )
        }
    ) { paddingValues ->
        when {
            isLoading -> LoadingView(modifier = Modifier.padding(paddingValues))
            showEmptyState -> EmptyListView(
                modifier = Modifier.padding(paddingValues),
                message = stringResource(id = LocalizationR.string.trending_projects_empty_list_view_message)
            )

            showErrorState -> ErrorView(
                modifier = Modifier.padding(paddingValues),
                errorTitle = stringResource(id = LocalizationR.string.trending_projects_error_list_view_title),
                errorMessage = stringResource(id = LocalizationR.string.trending_projects_error_list_view_message)
            ) {
                viewModel.fetchTrendingProjects()
            }

            else -> TrendingProjectListContent(
                trendingProjects = trendingProjects,
                paddingValues = paddingValues,
                navigator = navigator,
                onFavoriteClicked = { trendingProjectId, isFavorite ->
                    viewModel.onFavoriteClicked(trendingProjectId, isFavorite)
                }
            )
        }
    }
}

@Composable
private fun TrendingProjectListContent(
    trendingProjects: List<TrendingProjectUiModel>,
    paddingValues: PaddingValues,
    navigator: DestinationsNavigator,
    onFavoriteClicked: (Int, Boolean) -> Unit
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(paddingValues),
) {
    trendingProjects.forEach {
        TrendingProjectCard(
            trendingProject = it,
            navigator = navigator,
            onFavoriteClicked = onFavoriteClicked
        )
    }
}

@Composable
fun TrendingProjectCard(
    trendingProject: TrendingProjectUiModel,
    navigator: DestinationsNavigator,
    onFavoriteClicked: (Int, Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(getContainerColor(), RoundedCornerShape(8.dp))
            .clickable {
                navigator.navigate(TrendingProjectDetailsScreenDestination(trendingProjectId = trendingProject.id))
            }
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = trendingProject.name,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = trendingProject.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            )
        }
        FavouriteIcon(onFavoriteClicked, trendingProject)

    }
}

@Composable
private fun FavouriteIcon(
    onFavoriteClicked: (Int, Boolean) -> Unit,
    trendingProject: TrendingProjectUiModel
) {
    IconButton(
        onClick = {
            onFavoriteClicked(
                trendingProject.id,
                trendingProject.isFavorite
            )
        }
    ) {
        Icon(
            imageVector = if (trendingProject.isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Outlined.FavoriteBorder
            },
            contentDescription = null,
            tint = if (trendingProject.isFavorite) {
                Color.Red
            } else {
                Color.Gray
            }
        )
    }
}

@Composable
private fun getContainerColor() = if (isSystemInDarkTheme()) {
    ColorsPalette.Gray1
} else {
    ColorsPalette.Gray2
}

