package com.example.trendingproject.details

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.design.composables.ErrorView
import com.example.trendingproject.TrendingProjectsGraph
import com.example.trendingproject.details.model.TrendingProjectDetailsUiModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.parameters.CodeGenVisibility.INTERNAL
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.localization.R as LocalizationR

@OptIn(ExperimentalMaterial3Api::class)
@Destination<TrendingProjectsGraph>(
    visibility = INTERNAL,
    style = AnimatedStyle.SlideInOut::class
)
@Composable
internal fun TrendingProjectDetailsScreen(
    trendingProjectId: Int,
    navigator: DestinationsNavigator,
    viewModel: TrendingProjectDetailsViewModel = hiltViewModel()
) {
    val details by viewModel.trendingProjectDetails.observeAsState(null)
    val showErrorState by viewModel.showErrorState.observeAsState(false)

    viewModel.getTrendingProjectDetails(trendingProjectId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = LocalizationR.string.trending_project_details_top_appbar_name))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigator.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        when {
            showErrorState -> ErrorView(
                modifier = Modifier.padding(paddingValues),
                errorTitle = stringResource(id = LocalizationR.string.trending_projects_error_list_view_title),
                errorMessage = stringResource(id = LocalizationR.string.trending_projects_error_list_view_message)
            ) {
                // do nothing
            }

            else -> TrendingProjectDetailsContent(
                trendingProject = details ?: return@Scaffold,
                paddingValues = paddingValues
            )
        }
    }
}

@Composable
private fun TrendingProjectDetailsContent(
    trendingProject: TrendingProjectDetailsUiModel,
    paddingValues: PaddingValues,
) = Column(
    Modifier
        .padding(paddingValues)
        .padding(4.dp)
        .background(
            color = getContainerColor(),
            shape = RoundedCornerShape(12.dp)
        )
        .fillMaxWidth()
        .padding(16.dp)
) {
    ProjectNameView(trendingProject)
    Spacer(modifier = Modifier.height(8.dp))
    DescriptionView(trendingProject)
    Spacer(modifier = Modifier.height(12.dp))
    OwnerNameView(trendingProject)
    Spacer(modifier = Modifier.height(8.dp))
    LanguageView(trendingProject)
    WatchersCountView(trendingProject)
    Spacer(modifier = Modifier.height(8.dp))
    ForkCountsView(trendingProject)
}

@Composable
private fun ProjectNameView(trendingProject: TrendingProjectDetailsUiModel) {
    Text(
        text = trendingProject.name,
        style = MaterialTheme.typography.titleLarge.copy(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Composable
private fun DescriptionView(trendingProject: TrendingProjectDetailsUiModel) {
    Text(
        text = trendingProject.description,
        style = MaterialTheme.typography.bodyMedium.copy(
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

@Composable
private fun OwnerNameView(trendingProject: TrendingProjectDetailsUiModel) {
    Text(
        text = stringResource(
            id = LocalizationR.string.trending_project_details_top_owner_label,
            trendingProject.ownerName
        ),
        style = MaterialTheme.typography.bodyMedium.copy(
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
private fun LanguageView(trendingProject: TrendingProjectDetailsUiModel) {
    trendingProject.language?.let {
        Text(
            text = stringResource(
                id = LocalizationR.string.trending_project_details_top_language_label,
                it
            ),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun WatchersCountView(trendingProject: TrendingProjectDetailsUiModel) {
    Text(
        text = stringResource(
            id = LocalizationR.string.trending_project_details_top_watchers_count_label,
            trendingProject.watchersCount
        ),
        style = MaterialTheme.typography.bodyMedium.copy(
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

@Composable
private fun ForkCountsView(trendingProject: TrendingProjectDetailsUiModel) {
    Text(
        text = stringResource(
            id = LocalizationR.string.trending_project_details_top_forks_count_label,
            trendingProject.forksCount
        ),
        style = MaterialTheme.typography.bodyMedium.copy(
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}


@Composable
private fun getContainerColor() = if (isSystemInDarkTheme()) {
    ColorsPalette.Gray1
} else {
    ColorsPalette.Gray2
}