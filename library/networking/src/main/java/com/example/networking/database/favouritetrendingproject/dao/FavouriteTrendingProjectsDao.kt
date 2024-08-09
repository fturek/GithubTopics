package com.example.networking.database.favouritetrendingproject.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.networking.database.favouritetrendingproject.FAVOURITE_TRENDING_PROJECTS
import com.example.networking.database.favouritetrendingproject.entity.FavouriteTrendingProjectEntity

@Dao
interface FavouriteTrendingProjectsDao {

    @Query("SELECT * FROM $FAVOURITE_TRENDING_PROJECTS")
    suspend fun getFavouriteTrendingProjectsId(): List<FavouriteTrendingProjectEntity>

    @Insert
    suspend fun insert(vararg entity: FavouriteTrendingProjectEntity)

    @Delete
    suspend fun delete(vararg entity: FavouriteTrendingProjectEntity)
}