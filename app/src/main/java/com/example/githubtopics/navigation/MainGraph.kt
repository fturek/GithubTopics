package com.example.githubtopics.navigation

import com.ramcosta.composedestinations.annotation.ExternalNavGraph
import com.ramcosta.composedestinations.annotation.NavHostGraph
import com.ramcosta.composedestinations.generated.trendingproject.navgraphs.TrendingProjectsNavGraph

@NavHostGraph
annotation class MainGraph {

    @ExternalNavGraph<TrendingProjectsNavGraph>(start = true)
    companion object Includes
}