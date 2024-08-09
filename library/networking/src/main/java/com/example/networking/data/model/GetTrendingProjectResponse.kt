package com.example.networking.data.model

import com.google.gson.annotations.SerializedName

data class GetTrendingProjectResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<TrendingProjectResponse>
)