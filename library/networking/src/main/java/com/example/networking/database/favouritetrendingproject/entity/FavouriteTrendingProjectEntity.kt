package com.example.networking.database.favouritetrendingproject.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.networking.database.favouritetrendingproject.FAVOURITE_TRENDING_PROJECTS

@Entity(tableName = FAVOURITE_TRENDING_PROJECTS)
data class FavouriteTrendingProjectEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val trendingProjectId: Int
)